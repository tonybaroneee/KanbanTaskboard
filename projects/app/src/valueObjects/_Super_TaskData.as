/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - TaskData.as.
 */

package valueObjects
{
import com.adobe.fiber.services.IFiberManagingService;
import com.adobe.fiber.valueobjects.IValueObject;
import flash.events.EventDispatcher;
import mx.events.PropertyChangeEvent;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_TaskData extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
        try
        {
            if (flash.net.getClassByAlias("kanbanbackend.data.TaskData") == null)
            {
                flash.net.registerClassAlias("kanbanbackend.data.TaskData", cz);
            }
        }
        catch (e:Error)
        {
            flash.net.registerClassAlias("kanbanbackend.data.TaskData", cz);
        }
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
    }

    model_internal var _dminternal_model : _TaskDataEntityMetadata;

    /**
     * properties
     */
    private var _internal_stageId : int;
    private var _internal_positStyle : String;
    private var _internal_postitWeight : String;
    private var _internal_description : String;
    private var _internal_postitFont : String;
    private var _internal_userId : int;
    private var _internal_columnIndex : int;
    private var _internal_positColor : int;
    private var _internal_postitSize : int;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_TaskData()
    {
        _model = new _TaskDataEntityMetadata(this);

        // Bind to own data properties for cache invalidation triggering

    }

    /**
     * data property getters
     */

    [Bindable(event="propertyChange")]
    public function get stageId() : int
    {
        return _internal_stageId;
    }

    [Bindable(event="propertyChange")]
    public function get positStyle() : String
    {
        return _internal_positStyle;
    }

    [Bindable(event="propertyChange")]
    public function get postitWeight() : String
    {
        return _internal_postitWeight;
    }

    [Bindable(event="propertyChange")]
    public function get description() : String
    {
        return _internal_description;
    }

    [Bindable(event="propertyChange")]
    public function get postitFont() : String
    {
        return _internal_postitFont;
    }

    [Bindable(event="propertyChange")]
    public function get userId() : int
    {
        return _internal_userId;
    }

    [Bindable(event="propertyChange")]
    public function get columnIndex() : int
    {
        return _internal_columnIndex;
    }

    [Bindable(event="propertyChange")]
    public function get positColor() : int
    {
        return _internal_positColor;
    }

    [Bindable(event="propertyChange")]
    public function get postitSize() : int
    {
        return _internal_postitSize;
    }

    /**
     * data property setters
     */

    public function set stageId(value:int) : void
    {
        var oldValue:int = _internal_stageId;
        if (oldValue !== value)
        {
            _internal_stageId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "stageId", oldValue, _internal_stageId));
        }
    }

    public function set positStyle(value:String) : void
    {
        var oldValue:String = _internal_positStyle;
        if (oldValue !== value)
        {
            _internal_positStyle = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "positStyle", oldValue, _internal_positStyle));
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

    public function set description(value:String) : void
    {
        var oldValue:String = _internal_description;
        if (oldValue !== value)
        {
            _internal_description = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "description", oldValue, _internal_description));
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

    public function set userId(value:int) : void
    {
        var oldValue:int = _internal_userId;
        if (oldValue !== value)
        {
            _internal_userId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "userId", oldValue, _internal_userId));
        }
    }

    public function set columnIndex(value:int) : void
    {
        var oldValue:int = _internal_columnIndex;
        if (oldValue !== value)
        {
            _internal_columnIndex = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "columnIndex", oldValue, _internal_columnIndex));
        }
    }

    public function set positColor(value:int) : void
    {
        var oldValue:int = _internal_positColor;
        if (oldValue !== value)
        {
            _internal_positColor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "positColor", oldValue, _internal_positColor));
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
    public function get _model() : _TaskDataEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _TaskDataEntityMetadata) : void
    {
        var oldValue : _TaskDataEntityMetadata = model_internal::_dminternal_model;
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
