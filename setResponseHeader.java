package 疫情;

/**
 * 向客户端发送响应流方法
 *
 * @param response
 * @param fileName
 */
public void setResponseHeader(HttpServletResponse response, String fileName) {
    try {
        try {
            fileName = new String(fileName.getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}