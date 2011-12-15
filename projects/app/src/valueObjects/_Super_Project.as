/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - Project.as.
 */

package valueObjects
{
import com.adobe.fiber.services.IFiberManagingService;
import com.adobe.fiber.valueobjects.IValueObject;
import flash.events.EventDispatcher;
import mx.events.PropertyChangeEvent;
import valueObjects.Workspace;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_Project extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
        try
        {
            if (flash.net.getClassByAlias("kanbanbackend.model.Project") == null)
            {
                flash.net.registerClassAlias("kanbanbackend.model.Project", cz);
            }
        }
        catch (e:Error)
        {
            flash.net.registerClassAlias("kanbanbackend.model.Project", cz);
        }
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        valueObjects.Workspace.initRemoteClassAliasSingleChild();
        valueObjects.Stage.initRemoteClassAliasSingleChild();
        valueObjects.Task.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _ProjectEntityMetadata;

    /**
     * properties
     */
    private var _internal_id : int;
    private var _internal_startDate : String;
    private var _internal_workspace : valueObjects.Workspace;
    private var _internal_name : String;
    private var _internal_leadDeveloper : String;
    private var _internal_company : String;
    private var _internal_projectManager : String;
    private var _internal_endDate : String;
    private var _internal_version : int;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_Project()
    {
        _model = new _ProjectEntityMetadata(this);

        // Bind to own data properties for cache invalidation triggering

    }

    /**
     * data property getters
     */

    [Bindable(event="propertyChange")]
    public function get id() : int
    {
        return _internal_id;
    }

    [Bindable(event="propertyChange")]
    public function get startDate() : String
    {
        return _internal_startDate;
    }

    [Bindable(event="propertyChange")]
    public function get workspace() : valueObjects.Workspace
    {
        return _internal_workspace;
    }

    [Bindable(event="propertyChange")]
    public function get name() : String
    {
        return _internal_name;
    }

    [Bindable(event="propertyChange")]
    public function get leadDeveloper() : String
    {
        return _internal_leadDeveloper;
    }

    [Bindable(event="propertyChange")]
    public function get company() : String
    {
        return _internal_company;
    }

    [Bindable(event="propertyChange")]
    public function get projectManager() : String
    {
        return _internal_projectManager;
    }

    [Bindable(event="propertyChange")]
    public function get endDate() : String
    {
        return _internal_endDate;
    }

    [Bindable(event="propertyChange")]
    public function get version() : int
    {
        return _internal_version;
    }

    /**
     * data property setters
     */

    public function set id(value:int) : void
    {
        var oldValue:int = _internal_id;
        if (oldValue !== value)
        {
            _internal_id = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "id", oldValue, _internal_id));
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

    public function set workspace(value:valueObjects.Workspace) : void
    {
        var oldValue:valueObjects.Workspace = _internal_workspace;
        if (oldValue !== value)
        {
            _internal_workspace = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "workspace", oldValue, _internal_workspace));
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

    public function set leadDeveloper(value:String) : void
    {
        var oldValue:String = _internal_leadDeveloper;
        if (oldValue !== value)
        {
            _internal_leadDeveloper = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "leadDeveloper", oldValue, _internal_leadDeveloper));
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

    public function set projectManager(value:String) : void
    {
        var oldValue:String = _internal_projectManager;
        if (oldValue !== value)
        {
            _internal_projectManager = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "projectManager", oldValue, _internal_projectManager));
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

    public function set version(value:int) : void
    {
        var oldValue:int = _internal_version;
        if (oldValue !== value)
        {
            _internal_version = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "version", oldValue, _internal_version));
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
    public function get _model() : _ProjectEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _ProjectEntityMetadata) : void
    {
        var oldValue : _ProjectEntityMetadata = model_internal::_dminternal_model;
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
