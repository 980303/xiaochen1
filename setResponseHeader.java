package ����;

/**
 * ��ͻ��˷�����Ӧ������
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