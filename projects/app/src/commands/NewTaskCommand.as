package commands
{
	import events.TaskEvent;
	
	import mx.controls.Alert;
	import mx.managers.PopUpManager;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import services.KanbanService;
	
	import valueObjects.Stage;
	import valueObjects.Task;

	public class NewTaskCommand extends BaseCommand
	{
		public function NewTaskCommand(event:TaskEvent):void
		{
			super( event, this.result_handler, this.fault_handler);
		}
		
		public function execute(): void {
			var e:TaskEvent = originEvent as TaskEvent;
			callresponder.token = KanbanService.getInstance().addTask( e.task, e.project.id, (e.project.workspace.stages.getItemAt(e.colIndex) as valueObjects.Stage).id );		
		}
		
		public function result_handler(event:ResultEvent):void {
			var e:TaskEvent = originEvent as TaskEvent;
			
			if(event.result) {
				e.taskboard.taskboard.columns.getItemAt(e.task.columnIndex).addTask(event.result as valueObjects.Task, e.project);
				
			} else {
				Alert.show("Something has gone terribly wrong. Fail");
			}
			
		}
		
		public function fault_handler(event:FaultEvent):void {
			Alert.show("Something has gone terribly wrong. Fail \n" + event.message );
			
		}
	}
}