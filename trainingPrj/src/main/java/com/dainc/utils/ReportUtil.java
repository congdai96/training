package com.dainc.utils;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.dainc.model.MstRoleModel;
import com.dainc.model.MstUserModel;

import jp.co.nobworks.openfunxion4.core.BlockLayout;
import jp.co.nobworks.openfunxion4.core.Line;
import jp.co.nobworks.openfunxion4.core.OpenFunXion;
import jp.co.nobworks.openfunxion4.core.OpenFunXionException;
import jp.co.nobworks.openfunxion4.core.Text;



public class ReportUtil {

	@Inject


    static ResourceBundle resourceBundle = ResourceBundle.getBundle("ReportFileLink");	//XMLファイルのリンク
	
	private static int pageNo, pageTotal;

    public static void exec(List<MstUserModel> dataList,List<MstRoleModel> roleList) {
		OpenFunXion ofx = new OpenFunXion(resourceBundle.getString("file_xml"));
		try {
			ofx.open(resourceBundle.getString("file_pdf"));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (OpenFunXionException e) {
			e.printStackTrace();
			return;
		}
		

		ArrayList<ArrayList<MstUserModel>> reportList = new  ArrayList<ArrayList<MstUserModel>>();
		List<MstUserModel> noRoleSonList = new ArrayList<MstUserModel>();
		for (Iterator i = roleList.iterator(); i.hasNext();) {	//帳票したいデータを役職によって分割する
			noRoleSonList.clear();
			MstRoleModel roleModel = (MstRoleModel) i.next();
			List<MstUserModel> sonList = new ArrayList<MstUserModel>();
			for (Iterator j = dataList.iterator(); j.hasNext();) {
				MstUserModel dataModel = (MstUserModel) j.next();
				if (roleModel.getAuthorityId() == dataModel.getAuthorityId()) {
					sonList.add(dataModel);
				} else if (dataModel.getAuthorityId()==0) {
					noRoleSonList.add(dataModel);
				}
			}
			if(sonList.size()!=0) {
				pageTotal = pageTotal + (sonList.size()+15)/16;
				reportList.add((ArrayList<MstUserModel>) sonList);
			}
		}
		if(noRoleSonList.size()!=0) {
			pageTotal = pageTotal + (noRoleSonList.size()+15)/16;
			reportList.add((ArrayList<MstUserModel>) noRoleSonList);
		}
		for (Iterator i = reportList.iterator(); i.hasNext();) {
			List<MstUserModel> sonList = (List<MstUserModel>) i.next();
			makePdf(ofx, sonList);
		}
		ofx.out();
		pageNo = 0;
		pageTotal = 0;
	}

    public static  void makePdf( OpenFunXion ofx, List<MstUserModel> dataList) {
    	ofx.newPage();
    	MstUserModel userModel = dataList.get(0);
        if(userModel.getAuthorityId()==0) {
        	printOutline( ofx,"まだ登録していない");
        } else {
        	printOutline( ofx,userModel.getMstRoleModel().getAuthorityName());
        }


        int moveY = 40;

        pageNo++;
        Text page = ofx.getText( "text_13" );
        page.setMessage( "PAGE: " + pageNo +"/"+ pageTotal );
        page.print();


        int count = 0;

        BlockLayout dataBlock = ofx.getBlockLayout( "data_block" );
        dataBlock.resetPosition();
        Line rowLine = ofx.getLine( "row_line" );
        Text userId = ofx.getText( "row_user" );
        Text name = ofx.getText( "row_name" );
        Text sex = ofx.getText( "row_sex" );
        Text age = ofx.getText( "row_age" );
        Text no = ofx.getText("row_no");


        for ( Iterator it=dataList.iterator();it.hasNext(); ) {
            MstUserModel model = (MstUserModel)it.next();

            if ( count > 0 && count % 16 == 0 ) {
                ofx.newPage();
                dataBlock.resetPosition();
                printOutline( ofx,userModel.getMstRoleModel().getAuthorityName() );
                pageNo++;
                page.setMessage( "PAGE: " + pageNo +"/"+ pageTotal );
                page.print();
            }

            no.setMessage(String.valueOf(count+1));
            no.print();
            no.moveY( moveY );

            userId.setMessage( model.getUserId() );
            userId.print();
            userId.moveY( moveY );

            name.setMessage( model.getFirstName()+" "+model.getFamilyName() );
            name.print();
            name.moveY( moveY );



            if(model.getMstGenderModel().getGenderName()!=null) {
            	sex.setMessage(String.valueOf(model.getMstGenderModel().getGenderName()));}
            else {
            	sex.setMessage("");
            }
            sex.print();
            sex.moveY( moveY );

            if(model.getAge()!=0) {
            	age.setMessage(String.valueOf(model.getAge()));}
            else {
            	age.setMessage("");
            }
            age.print();
            age.moveY( moveY );

            rowLine.print();
            rowLine.moveY( moveY );

            count++;
        }
    }

    public static void printOutline( OpenFunXion ofx, String roleName ) {
        ofx.print( "body_block" );
        ofx.print( "title_1" );
        ofx.print( "header_box" );
        ofx.print( "header_1" );
        ofx.print( "header_2" );
        ofx.print( "header_3" );
        ofx.print( "header_4" );
        ofx.print( "header_5" );
        ofx.print( "out_box" );
        Text role =ofx.getText("text_15");
        Text time =ofx.getText("text_12");
        String timeStamp = new SimpleDateFormat("yyyy/M/dd HH:mm:ss").format(new Date());
        time.setMessage(timeStamp);
        time.print();
        role.setMessage(roleName);
        role.print();
    }



}