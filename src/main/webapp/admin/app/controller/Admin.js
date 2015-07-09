Ext.define('Admin.controller.Admin', {
    extend: 'Ext.app.Controller',

    stores: [
    	'Team@Admin.store',
    	'Role@Admin.store',
    	'User@Admin.store',
    	'TeamUser@Admin.store'
    ],

    models: [
    	'Team@Admin.model',
    	'Role@Admin.model',
    	'User@Admin.model',
    	'TeamUser@Admin.model'
    ],

    views: [
    	'Team@Admin.view',
    	'Role@Admin.view',
    	'User@Admin.view',
    	'TeamUser@Admin.view'
    ],

    init: function() {
    	this.control({
    		'role': {
    			deletebuttonclick: this.deleteRecord
    		},
    		'team': {
    			deletebuttonclick: this.deleteRecord
    		},
    		'user': {
    			deletebuttonclick: this.deleteRecord
    		},
    		'teamuser': {
    			deletebuttonclick: this.deleteRecord
    		},
            'team button[action=add]': {
                click: this.addTeam
            },
            'team button[action=search]': {
                click: this.search
            },
            'role button[action=add]': {
                click: this.addRole
            },
            'role button[action=search]': {
                click: this.search
            },
            'user button[action=add]': {
                click: this.addUser
            },
            
            'teamuser button[action=add]': {
                click: this.addTeamUser
            },
            'user button[action=search]': {
                click: this.search
            }
        });
    },
    
    addTeam : function(button) {
    	var form = button.up('form'),
			grid = form.ownerCt.down('grid'),
			tlstore = Ext.data.StoreManager.lookup('teamleader'),
			store = grid.store;	
		
		store.insert(0, Ext.create('Admin.model.Team', {
		    id   : '',
		    name : '',
		    teamleader : tlstore.getAt(0).get('id')
		}));
        grid.getPlugin('teamRowEditing').startEdit(0, 0);
    },
    
    addRole : function(button) {
    	var form = button.up('form'),
			grid = form.ownerCt.down('grid'),
			store = grid.store;	
		
		store.insert(0, Ext.create('Admin.model.Role', {
		    id   : '',
		    name : ''
		}));
        grid.getPlugin('roleRowEditing').startEdit(0, 0);
    },
    
    addUser : function(button) {
    	var form = button.up('form'),
			grid = form.ownerCt.down('grid'),
			store = grid.store;	
		
		store.insert(0, Ext.create('Admin.model.User', {
		    id   : '',
		    account : '',
		    password : '',
		    locked : true,
		    expired : true,
		    enabled : true
		}));
        grid.getPlugin('userRowEditing').startEdit(0, 0);
    },
    
    addTeamUser : function(button) {
    	var teamuser = button.up('teamuser'),
			grid = teamuser.down('grid'),
			store = grid.store;	
		
		store.insert(0, Ext.create('Admin.model.TeamUser', {
		    id   : '',
		    team_id : teamuser.teamid,
		    name : teamuser.name,
		    user_id : ''
		}));
        grid.getPlugin('teamUserRowEditing').startEdit(0, 0);
    },
    
    search : function(button) {
    	var form = button.up('form'),
    		values = form.getValues(),
    		store = form.ownerCt.down('grid').getStore();

    	Ext.apply(store.proxy.extraParams, values);
    	store.reload();
    },
    
    deleteRecord : function(grid, rowIndex, colIndex) {
    	Ext.Msg.show({
			title : 'Delete confirmation',
			message : 'Would you like to delete the record?',
			buttons : Ext.Msg.YESNO,
			icon : Ext.Msg.QUESTION,
			fn : function(btn) {
				if (btn === 'yes') {
					grid.store.removeAt(rowIndex);
				}
			}
		});
    }
});

