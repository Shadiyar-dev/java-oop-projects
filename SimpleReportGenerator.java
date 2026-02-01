class SimpleReportGenerator implements ReportGenerator {
    @Override
    public void generateReport(String data) {
        System.out.println("Генерация стандартного отчета: " + data);
    }
}