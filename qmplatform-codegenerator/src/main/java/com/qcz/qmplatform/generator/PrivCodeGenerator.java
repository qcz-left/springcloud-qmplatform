package com.qcz.qmplatform.generator;

import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.SqlConnRunner;
import com.qcz.qmplatform.common.utils.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import org.springframework.core.io.ClassPathResource;

import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限码生成器
 */
@SuppressWarnings("DuplicatedCode")
public class PrivCodeGenerator {

    public static void main(String[] args) throws Exception {
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new ClassPathResource("/ftl").getFile());
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        Template template = cfg.getTemplate("PrivCode.java.ftl");
        Template jsTemplate = cfg.getTemplate("priv_code.js.ftl");

        Map<String, Object> root = new HashMap<>();

        Connection connection = getConnection();
        SqlConnRunner sqlConnRunner = DbUtil.newSqlConnRunner(connection);
        List<Entity> sys_menu = sqlConnRunner.findAll(connection, "sys_menu");
        List<Entity> sys_button = sqlConnRunner.findAll(connection, "sys_button");

        List<Map<String, String>> menus = new ArrayList<>();
        for (Entity sysMenu : sys_menu) {
            String code = sysMenu.getStr("code");
            if (StringUtils.isBlank(code)) {
                continue;
            }
            Map<String, String> menu = new HashMap<>();
            menu.put("name", sysMenu.getStr("menu_name"));
            menu.put("code", code);
            menus.add(menu);
        }
        List<Map<String, String>> buttons = new ArrayList<>();
        for (Entity sysButton : sys_button) {
            String code = sysButton.getStr("code");
            if (StringUtils.isBlank(code)) {
                continue;
            }
            Map<String, String> btn = new HashMap<>();
            btn.put("name", sysButton.getStr("button_name"));
            btn.put("code", code);
            buttons.add(btn);
        }

        connection.close();

        root.put("menus", menus);
        root.put("buttons", buttons);

        Writer writer = new FileWriter("qmplatform-common\\src\\main\\java\\com\\qcz\\qmplatform\\common\\bean\\PrivCode.java");
        template.process(root, writer);
        writer.flush();
        writer.close();
        // js
        Writer jsWriter = new FileWriter(new ClassPathResource("./").getFile().getCanonicalPath() + "\\priv_code.js");
        jsTemplate.process(root, jsWriter);
        jsWriter.flush();
        jsWriter.close();
    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/qmplatform", "postgres", "postgres");
    }
}
