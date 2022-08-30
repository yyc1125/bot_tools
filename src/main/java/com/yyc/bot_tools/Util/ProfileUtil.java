package com.yyc.bot_tools.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyc.bot_tools.entities.Profile;
import com.yyc.bot_tools.entities.Result;
import com.yyc.bot_tools.service.profileService;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;


public class ProfileUtil {

    //导入用户

    public static Result importUser(MultipartFile multipartFile, profileService profileService) {

        Result result = new Result();

        try {

            // 获取原始名字

            String fileName = multipartFile.getOriginalFilename();

            // 获取后缀名

            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            //先将.json文件转为字符串类型

            File file = new File("/"+ fileName);

            //将MultipartFile类型转换为File类型

            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file);

            String jsonString = FileUtils.readFileToString(file, "UTF-8");



            //如果是json或者txt文件

            if (".json".equals(suffixName) || ".txt".equals(suffixName)) {



                //再将json字符串转为实体类

                JSONObject jsonObject = JSONObject.parseObject(jsonString);



                Profile profile = JSONObject.toJavaObject(jsonObject, Profile.class);



                profile.setId(null);

//                profile.setToken(UUID.randomUUID().toString());

                //调用创建用户的接口

                profileService.addProfile(profile);



            } else {

                result.setCode(400);

                result.setMessage("请上传正确格式的.json或.txt文件！");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return result;

    }

    //批量导入用户

//    public static Result importUsers(MultipartFile multipartFile, profileService profileService) {
//
//        Result resultModel = new Result();
//
//        try {
//
//            // 获取原始名字
//
//            String fileName = multipartFile.getOriginalFilename();
//
//            // 获取后缀名
//
//            String suffixName = fileName.substring(fileName.lastIndexOf("."));
//
//            //先将.json文件转为字符串类型
//
//            File file = new File("/"+ fileName);
//
//            //将MultipartFile类型转换为File类型
//
//            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),file);
//
//            String jsonString = FileUtils.readFileToString(file, "UTF-8");
//
//
//
//            //如果是json或者txt文件
//
//            if (".json".equals(suffixName) || ".txt".equals(suffixName)) {
//
//
//
//                //再将json字符串转为实体类
//
//                JSONObject jsonObject = JSONObject.parseObject(jsonString);
//
//
//
//                UserEntityList userEntityList = JSONObject.toJavaObject(jsonObject, UserEntityList.class);
//
//
//
//                List<UserEntity> userEntities = userEntityList.getUserEntities();
//
//                for (UserEntity userEntity : userEntities) {
//
//                    userEntity.setId(null);
//
//                    userEntity.setToken(UUID.randomUUID().toString());
//
//                    //调用创建用户的接口
//
//                    userService.addUser(userEntity);
//
//                }
//
//            } else {
//
//                resultModel.setStatusCode(0);
//
//                resultModel.setStatusMes("请上传正确格式的.json或.txt文件！");
//
//            }
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//        return resultModel;
//
//    }

    //导出某个用户

    public static Result exportUser(HttpServletResponse response, Profile profile, String fileName){

        Result result = new Result();

        ObjectMapper objectMapper = new ObjectMapper();

        if (ObjectUtils.isEmpty(profile)){

            result.setCode(400);

            result.setMessage("此用户id没有对应的用户");

            return result;

        }else {

            try {

                String jsonString = objectMapper.writeValueAsString(profile);



                // 拼接文件完整路径// 生成json格式文件

                String fullPath = "/" + fileName;



                // 保证创建一个新文件

                File file = new File(fullPath);

                if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录

                    file.getParentFile().mkdirs();

                }

                if (file.exists()) { // 如果已存在,删除旧文件

                    file.delete();

                }

                file.createNewFile();//创建新文件



                //将字符串格式化为json格式

                jsonString = jsonFormat(jsonString);

                // 将格式化后的字符串写入文件

                Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");

                write.write(jsonString);

                write.flush();

                write.close();



                FileInputStream fis = new FileInputStream(file);

                // 设置相关格式

                response.setContentType("application/force-download");

                // 设置下载后的文件名以及header

                response.setHeader("Content-Disposition", "attachment;filename="

                        .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));

                response.setCharacterEncoding("utf-8");

                // 创建输出对象

                OutputStream os = response.getOutputStream();

                // 常规操作

                byte[] buf = new byte[1024];

                int len = 0;

                while((len = fis.read(buf)) != -1) {

                    os.write(buf, 0, len);

                }

                fis.close();

                os.close();  //一定要记得关闭输出流，不然会继续写入返回实体模型

                return result;

            } catch (Exception e) {

                result.setCode(400);

                result.setMessage(e.getMessage());

                e.printStackTrace();

                return result;

            }

        }

    }

    //导出所有用户

    public static Result exportAllUser(HttpServletResponse response, List<Profile> profileList, String fileName){

        Result result = new Result();

        ObjectMapper objectMapper = new ObjectMapper();

        if (ObjectUtils.isEmpty(profileList)){

            result.setCode(400);

            result.setMessage("此用户id没有对应的用户");

            return result;

        }else {

            try {

                String jsonString = objectMapper.writeValueAsString(profileList);



                // 拼接文件完整路径// 生成json格式文件

                String fullPath = "/" + fileName;



                // 保证创建一个新文件

                File file = new File(fullPath);

                if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录

                    file.getParentFile().mkdirs();

                }

                if (file.exists()) { // 如果已存在,删除旧文件

                    file.delete();

                }

                file.createNewFile();//创建新文件



                //将字符串格式化为json格式

                jsonString = jsonFormat(jsonString);

                // 将格式化后的字符串写入文件

                Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");

                write.write(jsonString);

                write.flush();

                write.close();



                FileInputStream fis = new FileInputStream(file);

                // 设置相关格式

                response.setContentType("application/force-download");

                // 设置下载后的文件名以及header

                response.setHeader("Content-Disposition", "attachment;filename="

                        .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));

                response.setCharacterEncoding("utf-8");

                // 创建输出对象

                OutputStream os = response.getOutputStream();

                // 常规操作

                byte[] buf = new byte[1024];

                int len = 0;

                while((len = fis.read(buf)) != -1) {

                    os.write(buf, 0, len);

                }

                fis.close();

                os.close();     //一定要记得关闭输出流，不然会继续写入返回实体模型

                return result;

            } catch (Exception e) {

                result.setCode(400);

                result.setMessage(e.getMessage());

                e.printStackTrace();

                return result;

            }

        }

    }

    //将字符串格式化为json格式的字符串

    public static String jsonFormat(String jsonString) {

        JSONObject object= JSONObject.parseObject(jsonString);

        jsonString = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);

        return jsonString;

    }

}