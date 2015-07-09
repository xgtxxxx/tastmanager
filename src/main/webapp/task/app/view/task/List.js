Ext.define('Task.view.task.List', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.tasklist',

	// the default query condition
	defaultQuery : {},
	// the type of the panel, the value is 'own' or 'team'
	taskType : '',
	initComponent : function() {
		this.store = Ext.create('Task.store.Tasks');
		var isOwn = (this.taskType === 'own');
		Ext.apply(this.store.proxy.extraParams, this.defaultQuery);
		this.columns = [{
			xtype : 'rownumberer'
		}, {
			header : 'Task ID',
			dataIndex : 'taskId',
			width : 70,
			renderer : function(v){
				return "<a style='color:black;' data-qtip='Click to visit MA JIRA for task "+v+"' href='https://jira.marketamerica.com/browse/"+v+"' target='_blank'>"+v+"</a>";
			}
		}, {
			header : 'Team',
			dataIndex : 'teamName',
			hidden: !isOwn,
			width:90
		}, {
			header : 'Description',
			dataIndex : 'description',
			width : 390,
			renderer : function(v){
				v2 = v.replace(/\'/g, "&apos;");
				v2 = v2.replace(/\"/g, "&quot;");
				return "<span style='cursor:pointer;' data-qtip='"+v2+"'>"+v+"</span>";
			}
		}, {
			header : 'Release Version',
			width : 130,
			dataIndex : 'releaseVersion'
		}, {
			header : '%',
			dataIndex : 'progress',
			menuDisabled : true,
			width : 40
		}, {
			header : 'Status',
			dataIndex : 'statusDesc',
			width: 150,
			renderer : function(value, metaData, record, rowIndex, colIndex, store, view) {
				var color = record.data.bgColor;
				var fcolor = record.data.fontColor;
				metaData.tdStyle += 'background:' + color + ";color:" + fcolor + ";";
				return value;
			}
		}, {
			header : 'Assigned Date',
			dataIndex : 'assignDate'
		}, {
			header : 'Fixed Date',
			dataIndex : 'fixedDate'
		}, {
			header : 'Owner',
			sortable : false,
			dataIndex : 'ownerName',
			minWidth : 150,
			flex : 1
		}
		];
		
		
		if (isOwn) {
			this.columns.splice(1, 0, {
				xtype : 'actioncolumn',
				align : 'center',
				sortable : false,
				menuDisabled : true,
				width : 25,
				items : [{
					icon : contextPath + '/common/images/icons/shape_move_forwards.png',
					tooltip : 'Clone to New',
					handler : function(grid, rowIndex, colIndex) {
						var record = grid.getStore().getAt(rowIndex);
				    		var edit = Ext.create('Task.view.task.Edit', {
				    			submitUrl : contextPath + '/task/addTask',
				    			_grid : grid,
				    			renderTo : grid.ownerCt.getEl()
				    		}).show();
				    		edit.down('form').loadRecord(record);
				    		edit.clearId();
				    	}
				}]
			})
			this.columns.splice(1, 0, 
			{
				header : '',
				xtype : 'actioncolumn',
				align : 'center',
				sortable : false,
				menuDisabled : true,
				dataIndex : 'checked',
				width : 50,
				renderer : function(value, meta, record) {
					if (!value) {
						meta.tdStyle = "visibility:hidden";
					}
				},
				items : [ 
				          {
					icon : contextPath + '/common/images/icons/shape_square_edit.png',
					tooltip : 'Edit',
					handler : function(grid, rowIndex, colIndex) {
						var record = grid.getStore().getAt(rowIndex);
				    		var edit = Ext.create('Task.view.task.Edit', {
				    			submitUrl : contextPath + '/task/updateTask',
				    			_grid : grid,
				    			renderTo : grid.ownerCt.getEl()
				    		}).show();
				    		edit.down('form').loadRecord(record);
				    	}
				},{
					icon : contextPath + '/common/images/icons/shape_square_delete.png',
					tooltip : 'Delete',
					handler : function(grid, rowIndex, colIndex) {
						Ext.Msg.show({
							title : 'Delete confirmation',
							message : 'Would you like to delete the task?',
							buttons : Ext.Msg.YESNO,
							icon : Ext.Msg.QUESTION,
							fn : function(btn) {
								if (btn === 'yes') {
									var rec = grid.getStore().getAt(rowIndex);
									Ext.Ajax.request({
										url : contextPath + '/task/deleteTask',
										params : {
											id : rec.get('id')
										},
										success : function(response) {
											var res = Ext.decode(response.responseText);
											Ext.Msg.show({
												title : 'Reminder',
												message : res.message,
												buttons : Ext.Msg.OK,
												icon : res.success ? Ext.Msg.INFO : Ext.Msg.ERROR,
												fn : function(btn) {
													grid.getStore().reload();
												}
											});
										}
									});
								}
							}
						});
					}
				} 
				]
			});
			
		}else{
			this.columns.splice(1, 0, 
					{
						header : '',
						xtype : 'actioncolumn',
						align : 'center',
						sortable : false,
						menuDisabled : true,
						dataIndex : 'checked',
						width : 50,
						items : [ 
						          {
							icon : contextPath + '/common/images/icons/shape_square_edit.png',
							tooltip : 'Edit',
							handler : function(grid, rowIndex, colIndex) {
								var record = grid.getStore().getAt(rowIndex);
						    		var edit = Ext.create('Task.view.task.Edit', {
						    			submitUrl : contextPath + '/task/updateTask',
						    			_grid : grid,
						    			renderTo : grid.ownerCt.getEl()
						    		}).show();
						    		edit.down('form').loadRecord(record);
						    	}
						}
						]
					});
		}

		this.dockedItems = [ {
			xtype : 'pagingtoolbar',
			store : this.store,
			dock : 'bottom',
			displayInfo : true
		} ];

		this.features = [ {
			id : 'group',
			ftype : 'grouping',
			groupHeaderTpl : '{groupValue}',
			hideGroupedHeader : false,
			enableGroupingMenu : false
		} ];
		
		this.plugins=[{
			ptype: 'rowexpander',
			rowBodyTpl : new Ext.XTemplate(
				'<tpl if="commentsAndQuestions!=null && commentsAndQuestions!=\'\'">',
		            '<p><strong>Comments And Questions : </strong><font>{commentsAndQuestions}</font></p>',
		        '</tpl>',
		        '<tpl if="pendingIssues!=null && pendingIssues!=\'\'">',
		            '<p><strong>Pending Issues : </strong><font>{pendingIssues}</font></p>',
		        '</tpl>'
			)
		}];
		this.tbar = [{
			xtype : 'searchbar',
			border : false,
			parent : this,
			isOwn : this.taskType==='own'
		}]
		this.callParent(arguments);
		
		Ext.QuickTips.init();
	},
	reload : function(params){
		Ext.apply(this.store.proxy.extraParams,params);
		this.store.reload();
	},
	getTeam : function(){
		return this.defaultQuery.team;
	}
});
