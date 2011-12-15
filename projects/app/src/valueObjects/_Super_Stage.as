/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - Stage.as.
 */

package valueObjects
{
import com.adobe.fiber.services.IFiberManagingService;
import com.adobe.fiber.valueobjects.IValueObject;
import flash.events.EventDispatcher;
import mx.collections.ArrayCollection;
import mx.events.PropertyChangeEvent;
import valueObjects.Task;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_Stage extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
        try
        {
            if (flash.net.getClassByAlias("kanbanbackend.model.Stage") == null)
            {
                flash.net.registerClassAlias("kanbanbackend.model.Stage", cz);
            }
        }
        catch (e:Error)
        {
            flash.net.registerClassAlias("kanbanbackend.model.Stage", cz);
        }
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        valueObjects.Task.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _StageEntityMetadata;

    /**
     * properties
     */
    private var _internal_id : int;
    private var _internal_workspaceId : int;
    private var _internal_numTasks : int;
    private var _internal_name : String;
    private var _internal_orderNumber : int;
    private var _internal_capacity : int;
    private var _internal_tasks : ArrayCollection;
    model_internal var _internal_tasks_leaf:valueObjects.Task;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_Stage()
    {
        _model = new _StageEntityMetadata(this);

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
    public function get workspaceId() : int
    {
        return _internal_workspaceId;
    }

    [Bindable(event="propertyChange")]
    public function get numTasks() : int
    {
        return _internal_numTasks;
    }

    [Bindable(event="propertyChange")]
    public function get name() : String
    {
        return _internal_name;
    }

    [Bindable(event="propertyChange")]
    public function get orderNumber() : int
    {
        return _internal_orderNumber;
    }

    [Bindable(event="propertyChange")]
    public function get capacity() : int
    {
        return _internal_capacity;
    }

    [Bindable(event="propertyChange")]
    public function get tasks() : ArrayCollection
    {
        return _internal_tasks;
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

    public function set workspaceId(value:int) : void
    {
        var oldValue:int = _internal_workspaceId;
        if (oldValue !== value)
        {
            _internal_workspaceId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "workspaceId", oldValue, _internal_workspaceId));
        }
    }

    public function set numTasks(value:int) : void
    {
        var oldValue:int = _internal_numTasks;
        if (oldValue !== value)
        {
            _internal_numTasks = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "numTasks", oldValue, _internal_numTasks));
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

    public function set orderNumber(value:int) : void
    {
        var oldValue:int = _internal_orderNumber;
        if (oldValue !== value)
        {
            _internal_orderNumber = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "orderNumber", oldValue, _internal_orderNumber));
        }
    }

    public function set capacity(value:int) : void
    {
        var oldValue:int = _internal_capacity;
        if (oldValue !== value)
        {
            _internal_capacity = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "capacity", oldValue, _internal_capacity));
        }
    }

    public function set tasks(value:*) : void
    {
        var oldValue:ArrayCollection = _internal_tasks;
        if (oldValue !== value)
        {
            if (value is ArrayCollection)
            {
                _internal_tasks = value;
            }
            else if (value is Array)
            {
                _internal_tasks = new ArrayCollection(value);
            }
            else
            {
                throw new Error("value of tasks must be a collection");
            }
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "tasks", oldValue, _internal_tasks));
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
    public function get _model() : _StageEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _StageEntityMetadata) : void
    {
        var oldValue : _StageEntityMetadata = model_internal::_dminternal_model;
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
