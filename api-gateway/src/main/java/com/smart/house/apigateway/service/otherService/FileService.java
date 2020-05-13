package com.smart.house.apigateway.service.otherService;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Random;

@Service
public class FileService {
	
	@Value("${file.path:}")
	private String filePath;

	/**
	 * 1,files            ：form提交的文件 ，MultipartFile类型    比如：
	 * 2，filePath        ：上传文件的保存路径      比如：E:\Cit
	 * 3，localFile       ：上传之后的文件          比如：E:\Git\（时间戳）\ xxxxxxxxx.jpg
	 * 4，path            ：保存到数据库的相对路径   比如：\（时间戳）\ xxxxxxxxx.jpg
	 */
	public List<String> getImgPaths(List<MultipartFile> files) {
	    if (Strings.isNullOrEmpty(filePath)) {
            filePath = getResourcePath();
        }
		List<String> paths = Lists.newArrayList();
		files.forEach(file -> {
			File localFile = null;
			if (!file.isEmpty()) {
				try {
                    //MultipartFile类型文件写入file文件中
					localFile =  saveToLocal(file, filePath);
					//获取相对路径，除去前缀filepath 如 \（时间戳）\ xxxxxxxxx.jpg
					String path = StringUtils.substringAfterLast(localFile.getAbsolutePath(), filePath);
					paths.add(path);
				} catch (IOException e) {
					throw new IllegalArgumentException(e);
				}
			}
		});
		return paths;
	}
	
	public static String getResourcePath(){
	  File file = new File(".");
	  String absolutePath = file.getAbsolutePath();
	  return absolutePath;
	}

	private File saveToLocal(MultipartFile file, String filePath2) throws IOException {
		   String fileName = file.getOriginalFilename();                  //上传文件的真实路径名  如：C:/user/picture/ xxxxxx.jpg
		          fileName= fileName.substring(fileName.lastIndexOf("\\")+1);//获取最后反斜杠后的内容后缀   如：xxxxxx.jpg
	 //拼接路径  如 E:/Git/	（时间戳）/xxxxxx.jpg
	 File newFile = new File(filePath +fileName);
	 if (!newFile.exists()) {    //文件不存在，创建路径与文件
		 newFile.getParentFile().mkdirs();
		 newFile.createNewFile();
	 }
	 //文件写入
	 Files.write(file.getBytes(),newFile);
     return newFile;
	}

}
