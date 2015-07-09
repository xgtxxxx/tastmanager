Ext.define('Admin.view.Viewport', {
    extend: 'Ext.container.Viewport',
    
    layout: 'border',
    items: [{
    	region : 'center',
    	xtype : 'tabpanel',
    	items : [{
    		xtype : 'team'
    	} , {
    		xtype : 'role'
    	} , {
    		xtype : 'user'
    	}]
    }]
});