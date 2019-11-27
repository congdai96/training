package com.dainc.utils;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import com.dainc.model.MstUserModel;

import jp.co.nobworks.openfunxion4.core.BlockLayout;
import jp.co.nobworks.openfunxion4.core.Box;
import jp.co.nobworks.openfunxion4.core.Line;
import jp.co.nobworks.openfunxion4.core.OpenFunXion;
import jp.co.nobworks.openfunxion4.core.OpenFunXionException;
import jp.co.nobworks.openfunxion4.core.Text;
/*
 * バーコードフォントは http://itext.sourceforge.net/downloads/barcodefonts.zip より取得<BR>
 * サンプルプログラムはデバッグモードをＯＮにしています。（デザインツールの設定で指定）
 * デバッグモードにすることで、ツール上での確認と同様の動作をします。<BR>
 * （確認用作業ファイルの使用、確認用コマンドの実行）
 */


public class ReportUtil {
	
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("ReportFileLink");	//サーバーのxmlとPDF ファイルのリンク
    
    public static void exec(List<MstUserModel> dataList) {
        OpenFunXion ofx = new OpenFunXion( resourceBundle.getString("file_xml") );                        // 帳票情報XMLファイルの読み込み
        try {                                                   
            ofx.open( resourceBundle.getString("file_pdf") );                                                           // 出力PDFファイルのオープン
        } catch ( IOException e ) {
            e.printStackTrace();   
            return;
        } catch ( OpenFunXionException e ) {
            e.printStackTrace();   
            return;
        }
        makePdf( ofx, dataList );                                                                        // PDFファイルへの出力処理
    }
   
    public static  void makePdf( OpenFunXion ofx, List<MstUserModel> dataList) {
        
    	MstUserModel userModel = dataList.get(0);
        int pageTotal = (dataList.size()+15)/16;
        // レイアウトの固定部を出力
        printOutline( ofx,userModel.getMstRoleModel().getAuthorityName() );
        
        // Y方向の移動量を決める
        int moveY = 40;
        
        int pageNo = 1;
//        // ページ部を取得
        Text page = ofx.getText( "text_13" );
//        // 初期ページ数の設定
        page.setMessage( "PAGE: " + pageNo +"/"+ pageTotal );
//        // ページ部の出力
        page.print();
        
        // 改ページ用のカウンタ
        int count = 0;
        
        // デザインツールで作成した各一覧項目の取得
        Line rowLine = ofx.getLine( "row_line" );
        Text userId = ofx.getText( "row_user" );
        Text name = ofx.getText( "row_name" );
        Text sex = ofx.getText( "row_sex" );
        Text age = ofx.getText( "row_age" );
        Text no = ofx.getText("row_no");

        // 上記は それぞれの型にあわせたメソッドで取得していますが
        // これはキャストして取得する例
        Box reverseRow = (Box)ofx.getPrintObject( "reverse_row" );
        
        BlockLayout dataBlock = ofx.getBlockLayout( "data_block" );
        for ( Iterator it=dataList.iterator();it.hasNext(); ) {
            MstUserModel model = (MstUserModel)it.next();
            
            if ( count > 0 && count % 16 == 0 ) {
                // 改ページ
                ofx.newPage();
                // 改ページしたので、位置を元に戻す
 
                // BlockLayout で指定すると簡単
                dataBlock.resetPosition();
                
                // 新しいページの固定部を出力
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
        // 終了処理
        ofx.out();
    }
    /**
     * 外観部分の出力
     */
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