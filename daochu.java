package ����;


@ResponseBody
@RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
    QRRecord obj = new QRRecord();
    obj.setPageStart(Integer.valueOf(request.getParameter("pageStart")));
    obj.setPageSize(Integer.valueOf(request.getParameter("pageSize")));
    obj.setTerm(request.getParameter("term"));
    List<QRRecord> list = qrRecordService.findQRRecordList(obj);
    String[] title = {"ѧ��", "����", "����Ƿ�Ӵ�ȷ����", "�Ƿ�ȷ��", "�Ƿ����人", "����"};
    String filename = "���ݱ�.xls";
    String sheetName = "message";
    String[][] content = new String[list.size()][7];
    try {
        for (int i = 0; i < list.size(); i++) {
            content[i][0] = String.valueOf(list.get(i).getRid());
            content[i][1] = list.get(i).getOwnerName();
            content[i][2] = list.get(i).getAddTime();
            content[i][3] = list.get(i).getDeviceName();
            content[i][4] = list.get(i).getLocation();
            content[i][5] = String.valueOf(list.get(i).getAimFloor());
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
    try {
        // ��Ӧ���ͻ���
        this.setResponseHeader(response, filename);
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}