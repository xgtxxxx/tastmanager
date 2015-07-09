Ext.define('Admin.store.Team', {
    extend: 'Ext.data.Store',
    model: 'Admin.model.Team',
    
    autoLoad: true,
    autoSync: true,
    pageSize : 0,
    
    proxy: {
        type: 'rest',
        url: contextPath + '/team',
        reader: {
            type: 'json',
            rootProperty : 'recordlist'
        },
        writer: {
        	type : 'json'
        }
    },
    listeners: {
        write: function(store, operation){
        	//resole the message after operation(CUD)
//            var response = Ext.decode(operation._response.responseText);
//            
//			var tip = response.success ? 'Success' : 'Failure';
//        	Ext.Msg.alert(tip, response.message ? response.message : tip);
        	store.reload();
        }
    }
});