/**
 * UserData.as
 * 
 * valueObject for the attributes of a newly registered user.
 * 
 **/ 

package valueObjects
{
	[Bindable]
	[RemoteClass(alias="kanbanbackend.data.UserData")]
	public class UserData
	{
		public var firstName:String;
		public var lastName:String;
		public var email:String;
		public var password:String;
		
		public function UserData(firstName:String, lastName:String, email:String, password:String)
		{
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
		}
	}
}