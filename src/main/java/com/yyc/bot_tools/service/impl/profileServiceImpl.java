package com.yyc.bot_tools.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.yyc.bot_tools.Util.JigUtil;
import com.yyc.bot_tools.Util.ProfileConvert;
import com.yyc.bot_tools.dao.profileMapper;
import com.yyc.bot_tools.entities.KylinProfile;
import com.yyc.bot_tools.entities.Profile;
import com.yyc.bot_tools.entities.Result;
import com.yyc.bot_tools.entities.KodaiProfile;
import com.yyc.bot_tools.service.profileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class profileServiceImpl implements profileService {
    public static final String fileName = "profile";

    @Autowired
    private profileMapper profileMapper;

    @Override
    public int addProfile(Profile profile) {
        return profileMapper.addProfile(profile);
    }

    @Override
    public List<Profile> selectAllProfile(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return profileMapper.selectAllProfile();
    }

    @Override
    public int updateProfile(Profile profile,String[] checkboxGroup) {
        List<String> jigs = Arrays.asList(checkboxGroup);
        boolean jigEmail = jigs.contains("修饰邮箱");
        boolean jigPhone = jigs.contains("修饰电话号码");
        boolean jigAdress = jigs.contains("修饰地址");
        String email = profile.getEmail();
        String phone = profile.getPhone();
        String address = profile.getAddress1();
        if (jigEmail) {
            profile.setEmail(JigUtil.jigEmail(email));
        }
        if (jigPhone) {
            profile.setPhone(JigUtil.jigPhone(phone));
        }
        if (jigAdress) {
            profile.setAddress1(JigUtil.jigAddress(address));
        }
        return profileMapper.updateProfile(profile);
    }

    @Override
    public Profile selectProfileById(Integer id) {
        return profileMapper.selectProfileById(id);
    }

    @Override
    public int deleteProfileById(Integer id) {
        return profileMapper.deleteProfileById(id);
    }

    @Override
    public int countProfileNum() {
        return profileMapper.countProfileNum();
    }

    @Override
    public int batchDeleteProfile(int[] idList) {
        int count = 0;
        for (int i = 0; i < idList.length; i++) {
            count = count + profileMapper.deleteProfileById(idList[i]);
        }
        return count;
    }

    @Override
    public int batchUpdateProfile(int[] idList, Profile profile, String[] checkboxGroup) {
        int count = 0;
        List<String> jigs = Arrays.asList(checkboxGroup);
        boolean jigEmail = jigs.contains("修饰邮箱");
        boolean jigPhone = jigs.contains("修饰电话号码");
        boolean jigAdress = jigs.contains("修饰地址");
        for (int i = 0; i < idList.length; i++) {
            profile.setId(idList[i]);
            String email = profile.getEmail();
            String phone = profile.getPhone();
            String address = profile.getAddress1();
            if (jigEmail) {
                profile.setEmail(JigUtil.jigEmail(email));
            }
            if (jigPhone) {
                profile.setPhone(JigUtil.jigPhone(phone));
            }
            if (jigAdress) {
                profile.setAddress1(JigUtil.jigAddress(address));
            }
            count = count + profileMapper.updateProfile(profile);
        }
        return count;
    }

    @Override
    public Result exportProfile(HttpServletResponse response, int[] idList) {
        List profileList = new ArrayList();
        Result result = new Result();
        for (int i = 0; i < idList.length; i++) {
            profileList.add(selectProfileById(idList[i]));
        }
        JSON.toJSON(profileList);
        ObjectMapper objectMapper = new ObjectMapper();
        if (ObjectUtils.isEmpty(profileList)) {
            result.setCode(400);
            result.setMessage("此用户id没有对应的用户");
            return result;
        } else {
            try {
                String jsonString = objectMapper.writeValueAsString(profileList);
                // 拼接文件完整路径// 生成json格式文件
                String fullPath = "/" + this.fileName + ".json";
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
                        .concat(String.valueOf(URLEncoder.encode(this.fileName + ".json", "UTF-8"))));
                response.setCharacterEncoding("utf-8");
                // 创建输出对象
                OutputStream os = response.getOutputStream();
                // 常规操作
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = fis.read(buf)) != -1) {
                    os.write(buf, 0, len);
                }
                fis.close();
                os.close();     //一定要记得关闭输出流，不然会继续写入返回实体模型
                return null;
            } catch (Exception e) {
                result.setCode(400);
                result.setMessage(e.getMessage());
                e.printStackTrace();
                return result;
            }
        }
    }

    @Override
    public Result importProfile(MultipartFile multipartFile) {
        Result result = new Result();
        try {
            // 获取原始名字
            String fileName = multipartFile.getOriginalFilename();
            // 获取后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //先将.json文件转为字符串类型
            File file = new File("/" + fileName);
            //将MultipartFile类型转换为File类型
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);
            String jsonString = FileUtils.readFileToString(file, "UTF-8");
            //如果是json或者txt文件
            if (".json".equals(suffixName) || ".txt".equals(suffixName)) {
                Profile profile = new Profile();
                if ("kodai.txt".equals(fileName)) {
                    JSONObject jsonObject = JSONObject.parseObject(jsonString);
                    //正式提取未知的key值
                    Iterator<String> iterator = jsonObject.keySet().iterator();
                    //循环并得到key列表
                    JSONObject jsonObject1 = new JSONObject();
                    KodaiProfile kodaiProfile = new KodaiProfile();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        Object next = jsonObject.get(key);
                        jsonObject1 = (JSONObject) next;
                        kodaiProfile = jsonObject1.toJavaObject(KodaiProfile.class);
                        profile = ProfileConvert.kodaiToProfile(kodaiProfile);
                        profile.setId(null);
                        //调用创建用户的接口
                        this.addProfile(profile);
                    }
                } else if ("kylin.json".equals(fileName)) {
                    KylinProfile kylinProfile = new KylinProfile();
                    JSONArray jsonArray = JSONArray.parseArray(jsonString);
                    List<KylinProfile> kylinProfiles = jsonArray.toJavaList(KylinProfile.class);
                    for (int i = 0; i < kylinProfiles.size(); i++) {
                        kylinProfile = kylinProfiles.get(i);
                        profile = ProfileConvert.kylinToProfile(kylinProfile);
                        profile.setId(null);
                        //调用创建用户的接口
                        this.addProfile(profile);
                    }
                }
                result.setCode(200);
                result.setMessage("导入成功！");
            } else {
                result.setCode(400);
                result.setMessage("请上传正确格式的.json或.txt文件！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //将字符串格式化为json格式的字符串
    public static String jsonFormat(String jsonString) {
        JSONArray object = JSONArray.parseArray(jsonString);
        jsonString = JSON.toJSONString(object, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);
        return jsonString;
    }
}
