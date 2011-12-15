package commands
{
	import events.UserEvent;
	
	import flash.events.Event;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import services.KanbanService;
	
	import valueObjects.User;
	
	public class RegisterCommand extends BaseCommand
	{
		public function RegisterCommand(event:UserEvent):void
		{
			super( event, this.result_handler, this.fault_handler);
		}
		
		public function execute(): void {
			var e:UserEvent = originEvent as UserEvent
			callresponder.token = KanbanService.getInstance().addNewUser(e.user);
		}
		
		public function result_handler(event:ResultEvent):void {
			var e:UserEvent = originEvent as UserEvent;
			if (event.result) {
				e.taskboard.user = event.result as User;
				e.taskboard.header.currentState = "loggedIn";
				e.taskboard.header.loggedInAsLabel.text = "Logged in as " + e.taskboard.user.email;
				e.taskboard.currentState='selectProjectScreen';
				e.taskboard.selectproject.browse();
			} else {
				Alert.show("A user with that e-mail address already exists. Please try again.");
			}		
			
		}
		
		public function fault_handler(event:FaultEvent):void {
			Alert.show("Something went terribly wrong... the new user wasn't persisted to the database OHZ NOEZ");
		}
	}
}