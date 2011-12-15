package commands
{
	import events.UserEvent;
	
	import flash.events.Event;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.CallResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class BaseCommand
	{
		protected var callresponder:CallResponder;
		protected var originEvent:Event;
					
		public function BaseCommand(event:Event, result:Function, fault:Function):void
		{
			callresponder = new CallResponder();			
			callresponder.addEventListener(ResultEvent.RESULT, result);
			callresponder.addEventListener(FaultEvent.FAULT, fault);
			this.originEvent = event;
		}
		
	}
}