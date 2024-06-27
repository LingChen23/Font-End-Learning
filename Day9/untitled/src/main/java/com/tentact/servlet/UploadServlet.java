package com.tentact.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/uploadServlet")
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "F:\\实训\\沈阳工业2024\\project1\\img";

    // 上传配置
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取文件上传的解析器
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");






        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
//                    解析请求对象
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        //文件名
                        String fileName = new File(item.getName()).getName();
                        //文件上传目录
                        String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        System.out.println("文件上传成功");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
