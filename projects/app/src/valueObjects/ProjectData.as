
package valueObjects
{
	[Bindable]
	[RemoteClass(alias="kanbanbackend.data.ProjectData")]
	public class ProjectData
	{
		public var userEmail:String;
	    public var name:String;
		public var company:String;
		public var manager:String;
		public var developer:String;
		public var startDate:String;
		public var endDate:String;
		public var postitFont:String;
		public var postitStyle:String;
		public var postitWeight:String;
		public var postitSize:int;
		public var postitColor:int;
		public var numColumns:int;
		public var colName1:String;
		public var colName2:String;
		public var colName3:String;
		public var colName4:String;
		public var colName5:String;
		public var colName6:String;
		public var colName7:String;
		public var id:int;
		public var users:Array;
		public var userToAdd:String;
		
		public function ProjectData(){
			
		}
	}
}