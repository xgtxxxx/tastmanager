Ext.define('Task.view.Viewport', {
    extend: 'Ext.container.Viewport',
    
    layout: 'fit',
    items: [{
    	region : 'center',
        xtype: 'tasktab'
    }]
});