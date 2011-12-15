package commands
{
	import events.UserEvent;
	
	import flash.events.Event;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	
	import services.KanbanService;
	
	import valueObjects.User;

	public class SignInCommand extends BaseCommand
	{
		public function SignInCommand(event:UserEvent):void
		{
			super( event, this.result_handler, this.fault_handler);
		}
		
		public function execute(): void {
			var e:UserEvent = originEvent as UserEvent
			callresponder.token = KanbanService.getInstance().signIn(e.user.email, e.user.password)
		}
		
		public function result_handler(event:ResultEvent):void {
			var e:UserEvent = originEvent as UserEvent;
			if (event.result as User) {
				e.taskboard.user = event.result as User;
				e.taskboard.header.currentState = "loggedIn";
				e.taskboard.header.loggedInAsLabel.text = "Logged in as " + e.taskboard.user.email;
				e.taskboard.currentState = 'selectProjectScreen';
				e.taskboard.selectproject.browse();

			} else {
				Alert.show("Invalid login credentials. Please try again.");
			}
	
		}
		
		public function fault_handler(event:FaultEvent):void {
			
			
		}
	}
}