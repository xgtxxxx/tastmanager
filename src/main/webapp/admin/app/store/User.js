Ext.define('Admin.store.User', {
    extend: 'Ext.data.Store',
    model: 'Admin.model.User',
    
    autoLoad: true,
    autoSync: true,
    pageSize : 0,
    
    proxy: {
        type: 'rest',
        url: contextPath + '/user',
        reader: {
            type: 'json',
            rootProperty : 'recordlist'
        },
        writer: {
        	type : 'json'
        },
    	listeners: {
    		'exception' : function(store, request, operation, eOpts) {
    			var response = Ext.decode(request.responseText);
    			
    			Ext.Msg.alert('Failure', response.message);
    		}
    	}
    },
    listeners: {
        write: function(store, operation){
        	//resole the message after operation(CUD)
//            var response = Ext.decode(operation._response.responseText);
//			if (!response.success) {
//	        	Ext.Msg.alert('Failure', response.message);
//			}
        	store.reload();
        }
    }
});