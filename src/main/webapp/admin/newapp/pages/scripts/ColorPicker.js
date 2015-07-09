Ext.define('Admin.ColorPicker', {
	extend : 'Ext.form.field.Picker',
	alias : 'widget.smmcolorpicker',
	triggerTip : 'Please select a color.',
	tplId : '',
	initComponent: function() {
		this.tplId='_color'+new Date().getTime();
		this.tpl = "<div id='"+this.tplId+"'></div>"
		this.picker = Ext.create('Ext.picker.Color', {
			pickerField : this,
			ownerCt : this,
			floating : true,
			focusOnShow : true,
			style : {
				backgroundColor : "#fff"
			}
		});
		this.callParent(arguments);
	},
	listeners : {   
        'expand' : function() { 
                if (!this.picker.rendered && this.tpl) {   
                    this.picker.render(this.tplId);   
                }   
                this.picker.show();   
         },
        'render' : function() {   
                this.picker.on('select', this._select, this);   
            }   
    },
    _select : function(field,value,opts){
    	this.setValue("#"+value);
//    	this.inputEl.setStyle({
//			backgroundColor :'#'+value
//		});
    	this.collapse();
    }
});
