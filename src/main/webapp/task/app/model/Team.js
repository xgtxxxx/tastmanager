Ext.define('Task.model.Team', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.reader.Json'
    ],

    fields: [
         'id', 
         'name'
    ]
});