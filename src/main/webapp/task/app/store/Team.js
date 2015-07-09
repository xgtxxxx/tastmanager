Ext.define('Task.store.Team', {
    extend: 'Ext.data.Store',
    model: 'Task.model.Team',
    autoLoad: true,
    proxy: {
        type: 'ajax',
        url: contextPath + '/team/getAll',
        reader: {
            type: 'json',
            rootProperty : 'recordlist',
            totalProperty : 'totalcount'
        }
    }
});