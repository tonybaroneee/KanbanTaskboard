/**
 * TaskData.as
 * 
 * valueObject for the attributes of a newly created task.
 * 
 * abarone - 10/10/10
 **/ 

package valueObjects
{
	[Bindable]
	[RemoteClass(alias="kanbanbackend.data.TaskData")]
	public class TaskData
	{
		public var description:String;
		public var columnIndex:int;
		
		public var stageId:int;
		public var userId:int;
		
		public function TaskData(description:String=null,columnIndex:int=-1,stageId:int=-1,userId:int=-1 )
		{
			this.description=description;
			this.columnIndex=columnIndex;
			this.stageId=stageId;
			this.userId=userId;
		}
	}
}