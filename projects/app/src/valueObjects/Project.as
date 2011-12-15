package valueObjects
{
	
	[Bindable]
	[RemoteClass(alias="kanbanbackend.model.Project")]
	public class Project
	{
		public var id:int;
		public var name:String;
		public var company:String;
		public var projectManager:String;
		public var leadDeveloper:String;
		public var startDate:String;
		public var endDate:String;
		public var version:int;
		public var workspace:Workspace;
		
		public function Project()
		{
		}
				
	}
}