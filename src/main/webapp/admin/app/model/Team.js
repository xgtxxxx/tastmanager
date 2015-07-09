Ext.define('Admin.model.Team', {
    extend: 'Ext.data.Model',

    requires: [
        'Ext.data.reader.Json'
    ],

    fields: [
    	'id', 
        'name',
        'teamleader'
    ],
    validations: [{
        type: 'length',
        field: 'name',
        min: 1
    }]
});