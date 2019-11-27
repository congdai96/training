package com.dainc.controller.admin;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dainc.model.MstUserModel;
import com.dainc.service.IMstUserService;
import com.dainc.utils.FormUtil;
import com.dainc.utils.ReportUtil;


@WebServlet(urlPatterns = { "/admin-download" })
public class DownloadController extends HttpServlet {
	
  private static final long serialVersionUID = 1L;
  
  @Inject
  private IMstUserService mstUserService;
  
  ResourceBundle resourceBundle = ResourceBundle.getBundle("ReportFileLink");


  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	MstUserModel mstModel = FormUtil.toModel(MstUserModel.class, request);
	List<MstUserModel> result = mstUserService.search(mstModel);
	if(result!=null) {
		ReportUtil.exec(mstUserService.search(mstModel));	//帳票を作る
	    String fullPath = resourceBundle.getString("file_pdf");  // ファイルのリンク
	    Path path = Paths.get(fullPath);
	    byte[] data = Files.readAllBytes(path);
	    response.setContentType("application/octet-stream");	    // クライアントにるファイル設定
	    response.setHeader("Content-disposition", "attachment; filename=userreport.pdf");
	    response.setContentLength(data.length);
	    InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));	    // response outputstreamにfileを書く
	    OutputStream outStream = response.getOutputStream();
	    byte[] buffer = new byte[4096];
	    int bytesRead = -1;
	    while ((bytesRead = inputStream.read(buffer)) != -1) {
	      outStream.write(buffer, 0, bytesRead);
	    }
	    inputStream.close();
	    outStream.close();
	}
	else response.sendRedirect(request.getContextPath()+"/admin-user?type=list&message=not_haved&alert=danger");	//ダウンロードできない
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }
}
