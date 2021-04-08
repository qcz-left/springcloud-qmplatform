/*
 Navicat Premium Data Transfer

 Source Server         : 111.230.115.170-pg
 Source Server Type    : PostgreSQL
 Source Server Version : 120004
 Source Host           : 111.230.115.170:5432
 Source Catalog        : qmplatform
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 120004
 File Encoding         : 65001

 Date: 08/04/2021 14:30:42
*/


-- ----------------------------
-- Sequence structure for sys_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_user_id_seq";
CREATE SEQUENCE "public"."sys_user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 3
CACHE 1;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS "public"."clientdetails";
CREATE TABLE "public"."clientdetails" (
  "appid" varchar(256) COLLATE "pg_catalog"."default" NOT NULL,
  "resourceids" varchar(256) COLLATE "pg_catalog"."default",
  "appsecret" varchar(256) COLLATE "pg_catalog"."default",
  "scope" varchar(256) COLLATE "pg_catalog"."default",
  "granttypes" varchar(256) COLLATE "pg_catalog"."default",
  "redirecturl" varchar(256) COLLATE "pg_catalog"."default",
  "authorities" varchar(256) COLLATE "pg_catalog"."default",
  "access_token_validity" int4,
  "refresh_token_validity" int4,
  "additionalinformation" varchar(4096) COLLATE "pg_catalog"."default",
  "autoapprovescopes" varchar(256) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of clientdetails
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_access_token";
CREATE TABLE "public"."oauth_access_token" (
  "token_id" varchar(256) COLLATE "pg_catalog"."default",
  "token" bytea,
  "authentication_id" varchar(256) COLLATE "pg_catalog"."default" NOT NULL,
  "user_name" varchar(256) COLLATE "pg_catalog"."default",
  "client_id" varchar(256) COLLATE "pg_catalog"."default",
  "authentication" bytea,
  "refresh_token" varchar(256) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_approvals";
CREATE TABLE "public"."oauth_approvals" (
  "userid" varchar(256) COLLATE "pg_catalog"."default",
  "clientid" varchar(256) COLLATE "pg_catalog"."default",
  "scope" varchar(256) COLLATE "pg_catalog"."default",
  "status" varchar(10) COLLATE "pg_catalog"."default",
  "expiresat" timestamp(6),
  "lastmodifiedat" timestamp(6)
)
;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_client_details";
CREATE TABLE "public"."oauth_client_details" (
  "client_id" varchar(256) COLLATE "pg_catalog"."default" NOT NULL,
  "resource_ids" varchar(256) COLLATE "pg_catalog"."default",
  "client_secret" varchar(256) COLLATE "pg_catalog"."default",
  "scope" varchar(256) COLLATE "pg_catalog"."default",
  "authorized_grant_types" varchar(256) COLLATE "pg_catalog"."default",
  "web_server_redirect_uri" varchar(256) COLLATE "pg_catalog"."default",
  "authorities" varchar(256) COLLATE "pg_catalog"."default",
  "access_token_validity" int4,
  "refresh_token_validity" int4,
  "additional_information" varchar(4096) COLLATE "pg_catalog"."default",
  "autoapprove" varchar(256) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO "public"."oauth_client_details" VALUES ('client_1', NULL, '$2a$10$AITPnfou2evfgte63PP5mu.O3.ry0gsWLZqy/iOrPYtJmHrdhRWmu', 'all', 'authorization_code,password,refresh_token', NULL, NULL, 7200, 14400, NULL, NULL);

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_client_token";
CREATE TABLE "public"."oauth_client_token" (
  "token_id" varchar(256) COLLATE "pg_catalog"."default",
  "token" bytea,
  "authentication_id" varchar(256) COLLATE "pg_catalog"."default" NOT NULL,
  "user_name" varchar(256) COLLATE "pg_catalog"."default",
  "client_id" varchar(256) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_code";
CREATE TABLE "public"."oauth_code" (
  "code" varchar(256) COLLATE "pg_catalog"."default",
  "authentication" bytea
)
;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_refresh_token";
CREATE TABLE "public"."oauth_refresh_token" (
  "token_id" varchar(256) COLLATE "pg_catalog"."default",
  "token" bytea,
  "authentication" bytea
)
;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for sys_button
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_button";
CREATE TABLE "public"."sys_button" (
  "button_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "button_name" varchar(20) COLLATE "pg_catalog"."default",
  "menu_id" varchar(50) COLLATE "pg_catalog"."default",
  "code" varchar(255) COLLATE "pg_catalog"."default",
  "iorder" int4,
  "icon" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."sys_button"."button_id" IS '按钮id';
COMMENT ON COLUMN "public"."sys_button"."button_name" IS '按钮名称';
COMMENT ON COLUMN "public"."sys_button"."menu_id" IS '所属菜单id';
COMMENT ON COLUMN "public"."sys_button"."code" IS '编码';
COMMENT ON COLUMN "public"."sys_button"."iorder" IS '排序';
COMMENT ON COLUMN "public"."sys_button"."icon" IS '图标';
COMMENT ON TABLE "public"."sys_button" IS '按钮';

-- ----------------------------
-- Records of sys_button
-- ----------------------------
INSERT INTO "public"."sys_button" VALUES ('8', 'button1', '6', '6', 8, NULL);
INSERT INTO "public"."sys_button" VALUES ('3ccc109b-d75f-487e-a801-64e93465b118', 'name4的按钮', '4', 'name-btn', 0, NULL);
INSERT INTO "public"."sys_button" VALUES ('9ceffdf2-420e-40dc-9199-ab4ff049737b', '删除用户', 'ef07f63f-4ec1-4053-bb53-db7891359339', 'user-delete', 1, NULL);
INSERT INTO "public"."sys_button" VALUES ('4489ccf9-fec5-4f81-b817-0c1cd2c7a171', '保存用户', 'ef07f63f-4ec1-4053-bb53-db7891359339', 'user-save', 0, '');
INSERT INTO "public"."sys_button" VALUES ('985cf497-f3f0-4bea-8844-70fdc3ab4cde', '保存角色', '042a5960-785f-442e-a76a-576f7eb389c4', 'role-save', 0, NULL);
INSERT INTO "public"."sys_button" VALUES ('a9238df4-ca79-4ddb-afc9-74e01b808200', '删除角色', '042a5960-785f-442e-a76a-576f7eb389c4', 'role-delete', 1, NULL);
INSERT INTO "public"."sys_button" VALUES ('dd0268c3-1f3b-44f0-b94b-5d8df387019f', '分配角色', '042a5960-785f-442e-a76a-576f7eb389c4', 'role-allot', 2, NULL);
INSERT INTO "public"."sys_button" VALUES ('074f7b51-cb8c-4452-a188-e7afd4505930', '保存菜单', '82241eeb-ed7c-44f3-b9f0-56e986363907', 'menu-save', 0, NULL);
INSERT INTO "public"."sys_button" VALUES ('529279d9-6b31-4ed7-b61b-127f4257810e', '删除菜单', '82241eeb-ed7c-44f3-b9f0-56e986363907', 'menu-delete', 1, NULL);
INSERT INTO "public"."sys_button" VALUES ('117c2896-d27c-445d-bc88-240b8ac62032', '保存部门', '4af487ea-9903-4116-970e-d82dce9d49ce', 'org-save', 0, NULL);
INSERT INTO "public"."sys_button" VALUES ('e45cdf2e-692b-4ba3-aa65-464d8a9da97b', '删除部门', '4af487ea-9903-4116-970e-d82dce9d49ce', 'org-delete', 1, NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_menu";
CREATE TABLE "public"."sys_menu" (
  "menu_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "menu_name" varchar(100) COLLATE "pg_catalog"."default",
  "iorder" int4,
  "code" varchar(50) COLLATE "pg_catalog"."default",
  "icon" varchar(255) COLLATE "pg_catalog"."default",
  "link_url" varchar(255) COLLATE "pg_catalog"."default",
  "parent_id" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."sys_menu"."menu_name" IS '菜单名称';
COMMENT ON COLUMN "public"."sys_menu"."iorder" IS '排序';
COMMENT ON COLUMN "public"."sys_menu"."code" IS '编码';
COMMENT ON COLUMN "public"."sys_menu"."icon" IS '图标';
COMMENT ON COLUMN "public"."sys_menu"."link_url" IS '访问路径';
COMMENT ON COLUMN "public"."sys_menu"."parent_id" IS '父菜单id';
COMMENT ON TABLE "public"."sys_menu" IS '菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO "public"."sys_menu" VALUES ('b39a3944-08e9-42c3-8606-3db9b4974fe1', '电放锅的子菜单', 0, 'child', NULL, '', '7ab86504-87c1-41d4-abc7-7eea6112b967');
INSERT INTO "public"."sys_menu" VALUES ('bfba142d-6a80-417b-9e58-a3671283f2fb', '图标管理', 40, 'icon', NULL, '', '1');
INSERT INTO "public"."sys_menu" VALUES ('ec2d5dbf-ace1-4fe8-b85e-70fc84bf517e', '操作日志', 60, 'operate', NULL, '', '');
INSERT INTO "public"."sys_menu" VALUES ('b555a066-c76d-4b8f-a834-4998cc000711', '测试404', 70, 'ceshi404', NULL, '', '');
INSERT INTO "public"."sys_menu" VALUES ('e36040a7-f7cb-42f8-a55f-0a6f6e138958', '测试500', 80, 'ceshi500', NULL, '', '');
INSERT INTO "public"."sys_menu" VALUES ('1', '系统管理', 0, 'system', 'el-icon-s-tools', '', '');
INSERT INTO "public"."sys_menu" VALUES ('ef07f63f-4ec1-4053-bb53-db7891359339', '用户管理', 0, 'user', 'el-icon-user-solid', '', '1');
INSERT INTO "public"."sys_menu" VALUES ('82241eeb-ed7c-44f3-b9f0-56e986363907', '菜单管理', 50, 'menu', 'el-icon-menu', '', '1');
INSERT INTO "public"."sys_menu" VALUES ('4af487ea-9903-4116-970e-d82dce9d49ce', '部门管理', 30, 'dept', 'el-icon-s-home', '', '1');
INSERT INTO "public"."sys_menu" VALUES ('042a5960-785f-442e-a76a-576f7eb389c4', '角色管理', 1, 'role', 'el-icon-s-custom', '', '1');

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_organization";
CREATE TABLE "public"."sys_organization" (
  "organization_id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "organization_name" varchar(50) COLLATE "pg_catalog"."default",
  "organization_code" varchar(100) COLLATE "pg_catalog"."default",
  "parent_id" varchar(50) COLLATE "pg_catalog"."default",
  "remark" varchar(1000) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "iorder" int4
)
;
COMMENT ON COLUMN "public"."sys_organization"."organization_id" IS '组织机构id';
COMMENT ON COLUMN "public"."sys_organization"."organization_name" IS '组织机构名称';
COMMENT ON COLUMN "public"."sys_organization"."organization_code" IS '组织机构编码';
COMMENT ON COLUMN "public"."sys_organization"."parent_id" IS '父id';
COMMENT ON COLUMN "public"."sys_organization"."remark" IS '机构描述';
COMMENT ON COLUMN "public"."sys_organization"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."sys_organization"."iorder" IS '排序';
COMMENT ON TABLE "public"."sys_organization" IS '组织机构';

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO "public"."sys_organization" VALUES ('6afd2fc3-6619-4a05-b98c-8ca4d66dbc75', '管理部2', '2', '11f67a26-22da-49c1-86e8-78ee2a55ac7f', '2', '2020-11-01 16:24:07.543', 0);
INSERT INTO "public"."sys_organization" VALUES ('e087310b-b26f-4148-8fde-9d6c6ddf4f6e', '开发部2', 'develop2', '8b5afd90-02aa-4362-9ce6-751cf68ff41b', '', '2020-11-01 20:16:42.063', 0);
INSERT INTO "public"."sys_organization" VALUES ('526f57b8-6b09-407d-9099-96fa38719814', '某某信息科技有限公司', 'root', '', '根部门', '2020-11-06 21:01:03.995', 0);
INSERT INTO "public"."sys_organization" VALUES ('cbf0f864-9d11-4924-9351-e089a89f0db3', '人事部', 'renshi', '526f57b8-6b09-407d-9099-96fa38719814', '', '2020-11-06 21:00:25.746', 0);
INSERT INTO "public"."sys_organization" VALUES ('11f67a26-22da-49c1-86e8-78ee2a55ac7f', '管理部', 'manage', '526f57b8-6b09-407d-9099-96fa38719814', 'tt', '2020-11-01 16:03:54.036', 0);
INSERT INTO "public"."sys_organization" VALUES ('8b5afd90-02aa-4362-9ce6-751cf68ff41b', '开发部', 'develop', '526f57b8-6b09-407d-9099-96fa38719814', '', '2020-11-01 16:04:50.268', 1);
INSERT INTO "public"."sys_organization" VALUES ('784c2e87-b88a-46b8-8cd3-871e4646df36', '财务部', 'caiwu', '526f57b8-6b09-407d-9099-96fa38719814', 'sss', '2020-11-01 16:05:44.46', 2);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role";
CREATE TABLE "public"."sys_role" (
  "role_id" varchar(36) COLLATE "pg_catalog"."default" NOT NULL,
  "role_name" varchar(255) COLLATE "pg_catalog"."default",
  "role_sign" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON TABLE "public"."sys_role" IS '角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO "public"."sys_role" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', '系统管理员', 'system-admin', '最高长官');
INSERT INTO "public"."sys_role" VALUES ('67d86434-c302-4924-a75a-dfe5fbc4affd', '测试人员', 'ceshi', '测试人员');
INSERT INTO "public"."sys_role" VALUES ('3e8461bf-537d-4b7d-91db-9676a03fb6fb', '运维人员', 'web-operations', '运维人员');
INSERT INTO "public"."sys_role" VALUES ('test_tv', 'test_tv', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role_permission";
CREATE TABLE "public"."sys_role_permission" (
  "role_id" varchar(50) COLLATE "pg_catalog"."default",
  "permission_id" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON TABLE "public"."sys_role_permission" IS '角色-权限对应关系';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO "public"."sys_role_permission" VALUES ('67d86434-c302-4924-a75a-dfe5fbc4affd', '1');
INSERT INTO "public"."sys_role_permission" VALUES ('67d86434-c302-4924-a75a-dfe5fbc4affd', 'ef07f63f-4ec1-4053-bb53-db7891359339');
INSERT INTO "public"."sys_role_permission" VALUES ('67d86434-c302-4924-a75a-dfe5fbc4affd', '4489ccf9-fec5-4f81-b817-0c1cd2c7a171');
INSERT INTO "public"."sys_role_permission" VALUES ('67d86434-c302-4924-a75a-dfe5fbc4affd', '9ceffdf2-420e-40dc-9199-ab4ff049737b');
INSERT INTO "public"."sys_role_permission" VALUES ('67d86434-c302-4924-a75a-dfe5fbc4affd', 'b555a066-c76d-4b8f-a834-4998cc000711');
INSERT INTO "public"."sys_role_permission" VALUES ('67d86434-c302-4924-a75a-dfe5fbc4affd', 'e36040a7-f7cb-42f8-a55f-0a6f6e138958');
INSERT INTO "public"."sys_role_permission" VALUES ('test_tv', 'test_tv_pid2');
INSERT INTO "public"."sys_role_permission" VALUES ('3e8461bf-537d-4b7d-91db-9676a03fb6fb', 'ec2d5dbf-ace1-4fe8-b85e-70fc84bf517e');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', '1');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', 'ef07f63f-4ec1-4053-bb53-db7891359339');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', '4489ccf9-fec5-4f81-b817-0c1cd2c7a171');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', '9ceffdf2-420e-40dc-9199-ab4ff049737b');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', '042a5960-785f-442e-a76a-576f7eb389c4');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', '985cf497-f3f0-4bea-8844-70fdc3ab4cde');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', 'a9238df4-ca79-4ddb-afc9-74e01b808200');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', 'dd0268c3-1f3b-44f0-b94b-5d8df387019f');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', '4af487ea-9903-4116-970e-d82dce9d49ce');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', '117c2896-d27c-445d-bc88-240b8ac62032');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', 'e45cdf2e-692b-4ba3-aa65-464d8a9da97b');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', 'bfba142d-6a80-417b-9e58-a3671283f2fb');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', '82241eeb-ed7c-44f3-b9f0-56e986363907');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', '074f7b51-cb8c-4452-a188-e7afd4505930');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', '529279d9-6b31-4ed7-b61b-127f4257810e');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', 'ec2d5dbf-ace1-4fe8-b85e-70fc84bf517e');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', 'b555a066-c76d-4b8f-a834-4998cc000711');
INSERT INTO "public"."sys_role_permission" VALUES ('a77a83b4-abd7-4f3f-ac22-e914bb68ad5c', 'e36040a7-f7cb-42f8-a55f-0a6f6e138958');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user";
CREATE TABLE "public"."sys_user" (
  "id" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar COLLATE "pg_catalog"."default",
  "username" varchar(50) COLLATE "pg_catalog"."default",
  "loginname" varchar(50) COLLATE "pg_catalog"."default",
  "user_sex" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(1000) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "create_user_id" varchar(50) COLLATE "pg_catalog"."default",
  "phone" varchar(20) COLLATE "pg_catalog"."default",
  "email_addr" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."sys_user"."password" IS '密码';
COMMENT ON COLUMN "public"."sys_user"."username" IS '用户名';
COMMENT ON COLUMN "public"."sys_user"."loginname" IS '登录名';
COMMENT ON COLUMN "public"."sys_user"."user_sex" IS '用户性别';
COMMENT ON COLUMN "public"."sys_user"."remark" IS '备注';
COMMENT ON COLUMN "public"."sys_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."sys_user"."create_user_id" IS '创建人id';
COMMENT ON COLUMN "public"."sys_user"."phone" IS '手机号码';
COMMENT ON COLUMN "public"."sys_user"."email_addr" IS '邮箱地址';
COMMENT ON TABLE "public"."sys_user" IS '用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "public"."sys_user" VALUES ('105a8170-39eb-4c00-9061-9ac6dc915165', '$2a$10$V/Q6.69PWTRJ1XETKOoSPeRc7P8aO6dajko8CTM26BFlj7ZntWNE.', 'asdfaasdf', 'asdfsadfd', '2', '', NULL, NULL, '13123232323', 'sadfsdf@sfd.dd');
INSERT INTO "public"."sys_user" VALUES ('16', '$2a$10$OEZSm9DhiW7pHxPeAnRiBOqz2oAKzC13EwLcwG1PF2lKlDC4eJKrq', '我是DJ', 'ass', '1', '', NULL, NULL, '13187679076', 'asfsf@df.com');
INSERT INTO "public"."sys_user" VALUES ('12', '$2a$10$fGTnoqoIWd3mr86BqDJQOOHr3DiyVO0QrVo7zFVD247NiCEGJV3Y2', '打老虎', '456', '1', 'sfasfs', NULL, NULL, '18676767689', 'asdfasf@sfds.com');
INSERT INTO "public"."sys_user" VALUES ('10', '$2a$10$qfxTDehc7vdSKG3zafs9tOR14DfVZdwjmQ6/zTfuIM9PZeg2bwle.', '华为', 'sfdf', '1', 'sfasdf', NULL, NULL, '13123456678', '222@222.c');
INSERT INTO "public"."sys_user" VALUES ('15', '$2a$10$TYLiWGsoIdcngzCc6H2gwesbaDOLrkZNmn5JNn53plgfYU7ZogFQu', '李四', 'afsdf', '1', 'asd', NULL, NULL, '13187679076', 'asfsf@sdf.com');
INSERT INTO "public"."sys_user" VALUES ('17', '$2a$10$u8Gb7oKS6.e.9A4UGCFXr.ptOSjzHzYxriwRlG9x10ZYGqMnLHO9W', '东哥', 'asf', '1', 'as', NULL, NULL, '13245675432', '222@as.com');
INSERT INTO "public"."sys_user" VALUES ('4cee17bb-68f9-42a9-9b24-713c40b74d30', '$2a$10$BuoOFBeYXaFuPSFjWhUkU.42p8ACcjRR8UC2RjRxaJuci2OICyxIK', '隔壁老王', 'wang', '1', '', NULL, NULL, '13198989892', 'qcz_dfsdf@ll.com');
INSERT INTO "public"."sys_user" VALUES ('6b663de3-e983-4c8a-b900-dc0b66b2a801', '$2a$10$wSUkBWhKg7Xns.22mbfT3O.6zRb3SVj60x9brVgSEFh1CXcDhc0Va', '屈长忠', 'qcz', '1', '管理员1大概', '2020-10-01 12:51:42.935', NULL, '18675864534', 'qcz_left@163.com');
INSERT INTO "public"."sys_user" VALUES ('20a008c3-bc54-42a6-9822-ea4674aa5d1f', '$2a$10$d9dZPXtRUES8FM3hkRv4O.aHSXBuhTYKVd3ZjiuijEuPMIfteNiZa', '系统管理员', 'admin', '2', '', NULL, NULL, '18675864534', 'qcz_left@163.com');
INSERT INTO "public"."sys_user" VALUES ('2185d74c-df5c-4125-9d3b-9fd048118f73', '$2a$10$t91AUBlWbg4yWZzpWTOA6u6QvX7j2MmW9kukbvWcXweSVL1bZa8uy', '测试', 'test', '1', '可爱', '2020-10-03 13:33:58.702', NULL, '18787878787', '123r23#@dd.com');
INSERT INTO "public"."sys_user" VALUES ('1', '$2a$10$AITPnfou2evfgte63PP5mu.O3.ry0gsWLZqy/iOrPYtJmHrdhRWmu', 'user_1', 'user_1', '1', '备注', '2020-09-03 22:31:43', NULL, '18675864534', 'qcz_left@163.com');
INSERT INTO "public"."sys_user" VALUES ('e1abd44e-8022-4e81-bcf9-ed8863c889eb', '$2a$10$BCYA0L9cFj.pv0G8ROrMh.jiCnACnPzQFMJsFWe7hP4OAFFtW1QFK', 'sdf', 'sdf', '1', 'dhahaha', '2020-10-23 21:58:03.472', NULL, '13123454322', 'qzc@dd.com');
INSERT INTO "public"."sys_user" VALUES ('27b276c1-0faf-4090-9b29-bc4688c88d58', '$2a$10$k9KNEAvu9USDj03I4.TJqe5YVgMaGZitU59vTbKoBUMlRqOncOw4a', '陈秀凤', 'cxf', '2', '陈秀凤', NULL, NULL, '15244719906', '991528566@qq.com');
INSERT INTO "public"."sys_user" VALUES ('14', '$2a$10$a4F1A3GCjnFK7t3ZItnBf.l3bG9LEwiCCY56Pqki.Cf3hRhg.vHn.', '张三', 'sfd', '2', 'asfdsdf电饭锅', NULL, NULL, '13187679076', 'safs@com.asdf');
INSERT INTO "public"."sys_user" VALUES ('e5487ce7-b4ff-4ce5-bc94-931a0b908ea3', '$2a$10$3fuih7nUxniMynu9US.ZfuUdyChTd6aJ/AyluDjdNcKuogZYLv.3u', 'asf', '123', '1', '', NULL, NULL, '13123232323', 'qcz@df.com');

-- ----------------------------
-- Table structure for sys_user_organization
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user_organization";
CREATE TABLE "public"."sys_user_organization" (
  "user_id" varchar(50) COLLATE "pg_catalog"."default",
  "organization_id" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."sys_user_organization"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."sys_user_organization"."organization_id" IS '机构id';
COMMENT ON TABLE "public"."sys_user_organization" IS '用户-机构对应关系';

-- ----------------------------
-- Records of sys_user_organization
-- ----------------------------
INSERT INTO "public"."sys_user_organization" VALUES ('e5487ce7-b4ff-4ce5-bc94-931a0b908ea3', '784c2e87-b88a-46b8-8cd3-871e4646df36');
INSERT INTO "public"."sys_user_organization" VALUES ('105a8170-39eb-4c00-9061-9ac6dc915165', '8b5afd90-02aa-4362-9ce6-751cf68ff41b');
INSERT INTO "public"."sys_user_organization" VALUES ('16', '784c2e87-b88a-46b8-8cd3-871e4646df36');
INSERT INTO "public"."sys_user_organization" VALUES ('12', '6afd2fc3-6619-4a05-b98c-8ca4d66dbc75');
INSERT INTO "public"."sys_user_organization" VALUES ('10', NULL);
INSERT INTO "public"."sys_user_organization" VALUES ('15', NULL);
INSERT INTO "public"."sys_user_organization" VALUES ('17', NULL);
INSERT INTO "public"."sys_user_organization" VALUES ('4cee17bb-68f9-42a9-9b24-713c40b74d30', '11f67a26-22da-49c1-86e8-78ee2a55ac7f');
INSERT INTO "public"."sys_user_organization" VALUES ('6b663de3-e983-4c8a-b900-dc0b66b2a801', '8b5afd90-02aa-4362-9ce6-751cf68ff41b');
INSERT INTO "public"."sys_user_organization" VALUES ('20a008c3-bc54-42a6-9822-ea4674aa5d1f', '11f67a26-22da-49c1-86e8-78ee2a55ac7f');
INSERT INTO "public"."sys_user_organization" VALUES ('2185d74c-df5c-4125-9d3b-9fd048118f73', '8b5afd90-02aa-4362-9ce6-751cf68ff41b');
INSERT INTO "public"."sys_user_organization" VALUES ('27b276c1-0faf-4090-9b29-bc4688c88d58', 'cbf0f864-9d11-4924-9351-e089a89f0db3');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user_role";
CREATE TABLE "public"."sys_user_role" (
  "user_id" varchar(50) COLLATE "pg_catalog"."default",
  "role_id" varchar(50) COLLATE "pg_catalog"."default"
)
;
COMMENT ON TABLE "public"."sys_user_role" IS '用户角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO "public"."sys_user_role" VALUES ('1', '1');
INSERT INTO "public"."sys_user_role" VALUES ('6b663de3-e983-4c8a-b900-dc0b66b2a801', 'a77a83b4-abd7-4f3f-ac22-e914bb68ad5c');
INSERT INTO "public"."sys_user_role" VALUES ('6b663de3-e983-4c8a-b900-dc0b66b2a801', '67d86434-c302-4924-a75a-dfe5fbc4affd');
INSERT INTO "public"."sys_user_role" VALUES ('6b663de3-e983-4c8a-b900-dc0b66b2a801', '3e8461bf-537d-4b7d-91db-9676a03fb6fb');
INSERT INTO "public"."sys_user_role" VALUES ('20a008c3-bc54-42a6-9822-ea4674aa5d1f', 'a77a83b4-abd7-4f3f-ac22-e914bb68ad5c');
INSERT INTO "public"."sys_user_role" VALUES ('20a008c3-bc54-42a6-9822-ea4674aa5d1f', '67d86434-c302-4924-a75a-dfe5fbc4affd');
INSERT INTO "public"."sys_user_role" VALUES ('20a008c3-bc54-42a6-9822-ea4674aa5d1f', '3e8461bf-537d-4b7d-91db-9676a03fb6fb');
INSERT INTO "public"."sys_user_role" VALUES ('2185d74c-df5c-4125-9d3b-9fd048118f73', '67d86434-c302-4924-a75a-dfe5fbc4affd');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS "public"."test";
CREATE TABLE "public"."test" (
  "major1" int4,
  "minor1" int4,
  "name1" varchar COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO "public"."test" VALUES (1, 2, '31');

-- ----------------------------
-- Table structure for test2
-- ----------------------------
DROP TABLE IF EXISTS "public"."test2";
CREATE TABLE "public"."test2" (
  "major" int4,
  "minor" int4,
  "name" varchar COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of test2
-- ----------------------------
INSERT INTO "public"."test2" VALUES (1, 2, '3');

-- ----------------------------
-- View structure for v_sys_permission
-- ----------------------------
DROP VIEW IF EXISTS "public"."v_sys_permission";
CREATE VIEW "public"."v_sys_permission" AS  SELECT sys_menu.menu_id AS permission_id,
    sys_menu.menu_name AS permission_name,
    sys_menu.icon,
    sys_menu.code,
    sys_menu.iorder,
    sys_menu.parent_id,
    1 AS permission_type,
    sys_menu.link_url
   FROM sys_menu
UNION ALL
 SELECT sb.button_id AS permission_id,
    sb.button_name AS permission_name,
    sb.icon,
    sb.code,
    sb.iorder,
    sb.menu_id AS parent_id,
    2 AS permission_type,
    NULL::character varying AS link_url
   FROM sys_button sb
  WHERE (EXISTS ( SELECT 1
           FROM sys_menu sm
          WHERE ((sb.menu_id)::text = (sm.menu_id)::text)));
COMMENT ON VIEW "public"."v_sys_permission" IS 'permission_type（1：菜单，2：按钮）';

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."sys_user_id_seq"', 20, true);

-- ----------------------------
-- Primary Key structure for table clientdetails
-- ----------------------------
ALTER TABLE "public"."clientdetails" ADD CONSTRAINT "clientdetails_pkey" PRIMARY KEY ("appid");

-- ----------------------------
-- Primary Key structure for table oauth_access_token
-- ----------------------------
ALTER TABLE "public"."oauth_access_token" ADD CONSTRAINT "oauth_access_token_pkey" PRIMARY KEY ("authentication_id");

-- ----------------------------
-- Primary Key structure for table oauth_client_details
-- ----------------------------
ALTER TABLE "public"."oauth_client_details" ADD CONSTRAINT "oauth_client_details_pkey" PRIMARY KEY ("client_id");

-- ----------------------------
-- Primary Key structure for table oauth_client_token
-- ----------------------------
ALTER TABLE "public"."oauth_client_token" ADD CONSTRAINT "oauth_client_token_pkey" PRIMARY KEY ("authentication_id");

-- ----------------------------
-- Primary Key structure for table sys_button
-- ----------------------------
ALTER TABLE "public"."sys_button" ADD CONSTRAINT "sys_button_pkey" PRIMARY KEY ("button_id");

-- ----------------------------
-- Primary Key structure for table sys_menu
-- ----------------------------
ALTER TABLE "public"."sys_menu" ADD CONSTRAINT "sys_permission_pkey" PRIMARY KEY ("menu_id");

-- ----------------------------
-- Primary Key structure for table sys_organization
-- ----------------------------
ALTER TABLE "public"."sys_organization" ADD CONSTRAINT "sys_organization_pkey" PRIMARY KEY ("organization_id");

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "public"."sys_role" ADD CONSTRAINT "sys_role_pkey" PRIMARY KEY ("role_id");

-- ----------------------------
-- Uniques structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD CONSTRAINT "unique_loginname" UNIQUE ("loginname");
COMMENT ON CONSTRAINT "unique_loginname" ON "public"."sys_user" IS '登录名唯一';

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD CONSTRAINT "sys_user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table test2
-- ----------------------------
CREATE INDEX "idx_tbl_test2_major_minor" ON "public"."test2" USING btree (
  "major" "pg_catalog"."int4_ops" ASC NULLS LAST,
  "minor" "pg_catalog"."int4_ops" ASC NULLS LAST
);
