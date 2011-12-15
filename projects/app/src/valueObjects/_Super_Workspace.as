/**
 * This is a generated class and is not intended for modification.  To customize behavior
 * of this value object you may modify the generated sub-class of this class - Workspace.as.
 */

package valueObjects
{
import com.adobe.fiber.services.IFiberManagingService;
import com.adobe.fiber.valueobjects.IValueObject;
import flash.events.EventDispatcher;
import mx.collections.ArrayCollection;
import mx.events.PropertyChangeEvent;
import valueObjects.Stage;

import flash.net.registerClassAlias;
import flash.net.getClassByAlias;
import com.adobe.fiber.core.model_internal;
import com.adobe.fiber.valueobjects.IPropertyIterator;
import com.adobe.fiber.valueobjects.AvailablePropertyIterator;

use namespace model_internal;

[ExcludeClass]
public class _Super_Workspace extends flash.events.EventDispatcher implements com.adobe.fiber.valueobjects.IValueObject
{
    model_internal static function initRemoteClassAliasSingle(cz:Class) : void
    {
        try
        {
            if (flash.net.getClassByAlias("kanbanbackend.model.Workspace") == null)
            {
                flash.net.registerClassAlias("kanbanbackend.model.Workspace", cz);
            }
        }
        catch (e:Error)
        {
            flash.net.registerClassAlias("kanbanbackend.model.Workspace", cz);
        }
    }

    model_internal static function initRemoteClassAliasAllRelated() : void
    {
        valueObjects.Stage.initRemoteClassAliasSingleChild();
        valueObjects.Task.initRemoteClassAliasSingleChild();
    }

    model_internal var _dminternal_model : _WorkspaceEntityMetadata;

    /**
     * properties
     */
    private var _internal_id : int;
    private var _internal_postitColor : int;
    private var _internal_projectID : int;
    private var _internal_postitWeight : String;
    private var _internal_postitFont : String;
    private var _internal_name : String;
    private var _internal_versionId : int;
    private var _internal_numColumns : int;
    private var _internal_postitStyle : String;
    private var _internal_stages : ArrayCollection;
    model_internal var _internal_stages_leaf:valueObjects.Stage;
    private var _internal_postitSize : int;

    private static var emptyArray:Array = new Array();


    /**
     * derived property cache initialization
     */
    model_internal var _cacheInitialized_isValid:Boolean = false;

    model_internal var _changeWatcherArray:Array = new Array();

    public function _Super_Workspace()
    {
        _model = new _WorkspaceEntityMetadata(this);

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
    public function get postitColor() : int
    {
        return _internal_postitColor;
    }

    [Bindable(event="propertyChange")]
    public function get projectID() : int
    {
        return _internal_projectID;
    }

    [Bindable(event="propertyChange")]
    public function get postitWeight() : String
    {
        return _internal_postitWeight;
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
    public function get versionId() : int
    {
        return _internal_versionId;
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

    [Bindable(event="propertyChange")]
    public function get stages() : ArrayCollection
    {
        return _internal_stages;
    }

    [Bindable(event="propertyChange")]
    public function get postitSize() : int
    {
        return _internal_postitSize;
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

    public function set postitColor(value:int) : void
    {
        var oldValue:int = _internal_postitColor;
        if (oldValue !== value)
        {
            _internal_postitColor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "postitColor", oldValue, _internal_postitColor));
        }
    }

    public function set projectID(value:int) : void
    {
        var oldValue:int = _internal_projectID;
        if (oldValue !== value)
        {
            _internal_projectID = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "projectID", oldValue, _internal_projectID));
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

    public function set versionId(value:int) : void
    {
        var oldValue:int = _internal_versionId;
        if (oldValue !== value)
        {
            _internal_versionId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "versionId", oldValue, _internal_versionId));
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

    public function set stages(value:*) : void
    {
        var oldValue:ArrayCollection = _internal_stages;
        if (oldValue !== value)
        {
            if (value is ArrayCollection)
            {
                _internal_stages = value;
            }
            else if (value is Array)
            {
                _internal_stages = new ArrayCollection(value);
            }
            else
            {
                throw new Error("value of stages must be a collection");
            }
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "stages", oldValue, _internal_stages));
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
    public function get _model() : _WorkspaceEntityMetadata
    {
        return model_internal::_dminternal_model;
    }

    public function set _model(value : _WorkspaceEntityMetadata) : void
    {
        var oldValue : _WorkspaceEntityMetadata = model_internal::_dminternal_model;
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
