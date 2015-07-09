Ext.define('Admin.model.Role', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.reader.Json'
    ],

    fields: [
         'id', 
         'name'
    ],
    validations: [{
        type: 'length',
        field: 'name',
        min: 1
    }]
});