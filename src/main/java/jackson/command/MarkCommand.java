package jackson.command;

import jackson.exception.JacksonException;
import jackson.parser.Parser;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

public class MarkCommand extends Command {
    private String line;
    private String instruction;

    public MarkCommand(String line, String instruction) {
        this.line = line;
        this.instruction = instruction;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JacksonException {
        int taskNumber = Parser.updateMarkStatus(tasks, line);
        ui.printStatus(instruction, taskNumber, TaskList.getItemAtIndex(taskNumber - 1));
    }
}