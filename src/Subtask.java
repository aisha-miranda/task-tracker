public class Subtask extends Task{
    private Epic epic;

    public Subtask(String title, String description, Epic epic) {
        super(title, description);
        this.epic = epic;
    }

    @Override
    public void setStatus(String status) {
        super.setStatus(status);
        epic.updateStatus();
    }

    public Epic getEpic() {
        return epic;
    }
}
