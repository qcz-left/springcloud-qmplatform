/**
 * 此文件为 Freemarker 模板引擎自动生成，请勿修改
 */
export const PrivCode = {
<#list menus as menu>
  /**
   * ${menu.name}
   */
  MENU_CODE_${menu.code?upper_case?replace("-", "_")?replace(":", "_")?replace(" ", "")}: "${menu.code}",
</#list>
<#list buttons as button>
  /**
   * ${button.name}
   */
  BTN_CODE_${button.code?upper_case?replace("-", "_")?replace(":", "_")?replace(" ", "")}: "${button.code}",
</#list>
}
