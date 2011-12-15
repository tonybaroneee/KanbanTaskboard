/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - ProjectData.as.
 */

package valueObjects
{
import com.adobe.fiber.services.IFiberManagingService;
import com.adobe.fiber.valueobjects.IValueObject;
import flash.events.EventDispatcher;
import mx.collections.ArrayCollection;
import mx.events.PropertyChangeEvent;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_ProjectData extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
        try
        {
            if (flash.net.getClassByAlias("kanbanbackend.data.ProjectData") == null)
            {
                flash.net.registerClassAlias("kanbanbackend.data.ProjectData", cz);
            }
        }
        catch (e:Error)
        {
            flash.net.registerClassAlias("kanbanbackend.data.ProjectData", cz);
        }
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
    }

    model_internal var _dminternal_model : _ProjectDataEntityMetadata;

    /**
     * properties
     */
    private var _internal_postitColor : int;
    private var _internal_colName4 : String;
    private var _internal_colName3 : String;
    private var _internal_startDate : String;
    private var _internal_users : ArrayCollection;
    private var _internal_colName6 : String;
    private var _internal_manager : String;
    private var _internal_postitWeight : String;
    private var _internal_colName5 : String;
    private var _internal_colName7 : String;
    private var _internal_userEmail : String;
    private var _internal_endDate : String;
    private var _internal_postitSize : int;
    private var _internal_colName2 : String;
    private var _internal_colName1 : String;
    private var _internal_id : int;
    private var _internal_developer : String;
    private var _internal_postitFont : String;
    private var _internal_name : String;
    private var _internal_company : String;
    private var _internal_numColumns : int;
    private var _internal_postitStyle : String;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_ProjectData()
    {
        _model = new _ProjectDataEntityMetadata(this);

        // Bind to own data properties for cache invalidation triggering

    }

    /**
     * data property getters
     */

    [Bindable(event="propertyChange")]
    public function get postitColor() : int
    {
        return _internal_postitColor;
    }

    [Bindable(event="propertyChange")]
    public function get colName4() : String
    {
        return _internal_colName4;
    }

    [Bindable(event="propertyChange")]
    public function get colName3() : String
    {
        return _internal_colName3;
    }

    [Bindable(event="propertyChange")]
    public function get startDate() : String
    {
        return _internal_startDate;
    }

    [Bindable(event="propertyChange")]
    public function get users() : ArrayCollection
    {
        return _internal_users;
    }

    [Bindable(event="propertyChange")]
    public function get colName6() : String
    {
        return _internal_colName6;
    }

    [Bindable(event="propertyChange")]
    public function get manager() : String
    {
        return _internal_manager;
    }

    [Bindable(event="propertyChange")]
    public function get postitWeight() : String
    {
        return _internal_postitWeight;
    }

    [Bindable(event="propertyChange")]
    public function get colName5() : String
    {
        return _internal_colName5;
    }

    [Bindable(event="propertyChange")]
    public function get colName7() : String
    {
        return _internal_colName7;
    }

    [Bindable(event="propertyChange")]
    public function get userEmail() : String
    {
        return _internal_userEmail;
    }

    [Bindable(event="propertyChange")]
    public function get endDate() : String
    {
        return _internal_endDate;
    }

    [Bindable(event="propertyChange")]
    public function get postitSize() : int
    {
        return _internal_postitSize;
    }

    [Bindable(event="propertyChange")]
    public function get colName2() : String
    {
        return _internal_colName2;
    }

    [Bindable(event="propertyChange")]
    public function get colName1() : String
    {
        return _internal_colName1;
    }

    [Bindable(event="propertyChange")]
    public function get id() : int
    {
        return _internal_id;
    }

    [Bindable(event="propertyChange")]
    public function get developer() : String
    {
        return _internal_developer;
    }

    [Bindable(event="propertyChange")]
    public function get postitFont() : String
    {
        return _internal_postitFont;
    }

    [Bindable(event="propertyChange")]
    public function get name() : String
    {
        return _internal_name;
    }

    [Bindable(event="propertyChange")]
    public function get company() : String
    {
        return _internal_company;
    }

    [Bindable(event="propertyChange")]
    public function get numColumns() : int
    {
        return _internal_numColumns;
    }

    [Bindable(event="propertyChange")]
    public function get postitStyle() : String
    {
        return _internal_postitStyle;
    }

    /**
     * data property setters
     */

    public function set postitColor(value:int) : void
    {
        var oldValue:int = _internal_postitColor;
        if (oldValue !== value)
        {
            _internal_postitColor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "postitColor", oldValue, _internal_postitColor));
        }
    }

    public function set colName4(value:String) : void
    {
        var oldValue:String = _internal_colName4;
        if (oldValue !== value)
        {
            _internal_colName4 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "colName4", oldValue, _internal_colName4));
        }
    }

    public function set colName3(value:String) : void
    {
        var oldValue:String = _internal_colName3;
        if (oldValue !== value)
        {
            _internal_colName3 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "colName3", oldValue, _internal_colName3));
        }
    }

    public function set startDate(value:String) : void
    {
        var oldValue:String = _internal_startDate;
        if (oldValue !== value)
        {
            _internal_startDate = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "startDate", oldValue, _internal_startDate));
        }
    }

    public function set users(value:*) : void
    {
        var oldValue:ArrayCollection = _internal_users;
        if (oldValue !== value)
        {
            if (value is ArrayCollection)
            {
                _internal_users = value;
            }
            else if (value is Array)
            {
                _internal_users = new ArrayCollection(value);
            }
            else
            {
                throw new Error("value of users must be a collection");
            }
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "users", oldValue, _internal_users));
        }
    }

    public function set colName6(value:String) : void
    {
        var oldValue:String = _internal_colName6;
        if (oldValue !== value)
        {
            _internal_colName6 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "colName6", oldValue, _internal_colName6));
        }
    }

    public function set manager(value:String) : void
    {
        var oldValue:String = _internal_manager;
        if (oldValue !== value)
        {
            _internal_manager = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "manager", oldValue, _internal_manager));
        }
    }

    public function set postitWeight(value:String) : void
    {
        var oldValue:String = _internal_postitWeight;
        if (oldValue !== value)
        {
            _internal_postitWeight = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "postitWeight", oldValue, _internal_postitWeight));
        }
    }

    public function set colName5(value:String) : void
    {
        var oldValue:String = _internal_colName5;
        if (oldValue !== value)
        {
            _internal_colName5 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "colName5", oldValue, _internal_colName5));
        }
    }

    public function set colName7(value:String) : void
    {
        var oldValue:String = _internal_colName7;
        if (oldValue !== value)
        {
            _internal_colName7 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "colName7", oldValue, _internal_colName7));
        }
    }

    public function set userEmail(value:String) : void
    {
        var oldValue:String = _internal_userEmail;
        if (oldValue !== value)
        {
            _internal_userEmail = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "userEmail", oldValue, _internal_userEmail));
        }
    }

    public function set endDate(value:String) : void
    {
        var oldValue:String = _internal_endDate;
        if (oldValue !== value)
        {
            _internal_endDate = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "endDate", oldValue, _internal_endDate));
        }
    }

    public function set postitSize(value:int) : void
    {
        var oldValue:int = _internal_postitSize;
        if (oldValue !== value)
        {
            _internal_postitSize = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "postitSize", oldValue, _internal_postitSize));
        }
    }

    public function set colName2(value:String) : void
    {
        var oldValue:String = _internal_colName2;
        if (oldValue !== value)
        {
            _internal_colName2 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "colName2", oldValue, _internal_colName2));
        }
    }

    public function set colName1(value:String) : void
    {
        var oldValue:String = _internal_colName1;
        if (oldValue !== value)
        {
            _internal_colName1 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "colName1", oldValue, _internal_colName1));
        }
    }

    public function set id(value:int) : void
    {
        var oldValue:int = _internal_id;
        if (oldValue !== value)
        {
            _internal_id = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "id", oldValue, _internal_id));
        }
    }

    public function set developer(value:String) : void
    {
        var oldValue:String = _internal_developer;
        if (oldValue !== value)
        {
            _internal_developer = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "developer", oldValue, _internal_developer));
        }
    }

    public function set postitFont(value:String) : void
    {
        var oldValue:String = _internal_postitFont;
        if (oldValue !== value)
        {
            _internal_postitFont = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "postitFont", oldValue, _internal_postitFont));
        }
    }

    public function set name(value:String) : void
    {
        var oldValue:String = _internal_name;
        if (oldValue !== value)
        {
            _internal_name = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "name", oldValue, _internal_name));
        }
    }

    public function set company(value:String) : void
    {
        var oldValue:String = _internal_company;
        if (oldValue !== value)
        {
            _internal_company = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "company", oldValue, _internal_company));
        }
    }

    public function set numColumns(value:int) : void
    {
        var oldValue:int = _internal_numColumns;
        if (oldValue !== value)
        {
            _internal_numColumns = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "numColumns", oldValue, _internal_numColumns));
        }
    }

    public function set postitStyle(value:String) : void
    {
        var oldValue:String = _internal_postitStyle;
        if (oldValue !== value)
        {
            _internal_postitStyle = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "postitStyle", oldValue, _internal_postitStyle));
        }
    }

    /**
     * Data property setter listeners
     *
     * Each data property whose value affects other properties or the validity of the entity
     * needs to invalidate all previously calculated artifacts. These include:
     *  - any derived properties or constraints that reference the given data property.
     *  - any availability guards (variant expressions) that reference the given data property.
     *  - any style validations, message tokens or guards that reference the given data property.
     *  - the validity of the property (and the containing entity) if the given data property has a length restriction.
     *  - the validity of the property (and the containing entity) if the given data property is required.
     */


    /**
     * valid related derived properties
     */
    model_internal var _isValid : Boolean;
    model_internal var _invalidConstraints:Array = new Array();
    model_internal var _validationFailureMessages:Array = new Array();

    /**
     * derived property calculators
     */

    /**
     * isValid calculator
     */
    model_internal function calculateIsValid():Boolean
    {
        var violatedConsts:Array = new Array();
        var validationFailureMessages:Array = new Array();

        var propertyValidity:Boolean = true;

        model_internal::_cacheInitialized_isValid = true;
        model_internal::invalidConstraints_der = violatedConsts;
        model_internal::validationFailureMessages_der = validationFailureMessages;
        return violatedConsts.length == 0 && propertyValidity;
    }

    /**
     * derived property setters
     */

    model_internal function set isValid_der(value:Boolean) : void
    {
        var oldValue:Boolean = model_internal::_isValid;
        if (oldValue !== value)
        {
            model_internal::_isValid = value;
            _model.model_internal::fireChangeEvent("isValid", oldValue, model_internal::_isValid);
        }
    }

    /**
     * derived property getters
     */

    [Transient]
    [Bindable(event="propertyChange")]
    public function get _model() : _ProjectDataEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _ProjectDataEntityMetadata) : void
    {
        var oldValue : _ProjectDataEntityMetadata = model_internal::_dminternal_model;
        if (oldValue !== value)
        {
            model_internal::_dminternal_model = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_model", oldValue, model_internal::_dminternal_model));
        }
    }

    /**
     * methods
     */


    /**
     *  services
     */
    private var _managingService:com.adobe.fiber.services.IFiberManagingService;

    public function set managingService(managingService:com.adobe.fiber.services.IFiberManagingService):void
    {
        _managingService = managingService;
    }

    model_internal function set invalidConstraints_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_invalidConstraints;
        // avoid firing the event when old and new value are different empty arrays
        if (oldValue !== value && (oldValue.length > 0 || value.length > 0))
        {
            model_internal::_invalidConstraints = value;
            _model.model_internal::fireChangeEvent("invalidConstraints", oldValue, model_internal::_invalidConstraints);
        }
    }

    model_internal function set validationFailureMessages_der(value:Array) : void
    {
        var oldValue:Array = model_internal::_validationFailureMessages;
        // avoid firing the event when old and new value are different empty arrays
        if (oldValue !== value && (oldValue.length > 0 || value.length > 0))
        {
            model_internal::_validationFailureMessages = value;
            _model.model_internal::fireChangeEvent("validationFailureMessages", oldValue, model_internal::_validationFailureMessages);
        }
    }


}

}
