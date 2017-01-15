/**
 * 
 * 初始化类别菜单信息
 * 
 */
function initMenu(menuRegion)
{
	var menu = new Ext.menu.Menu({
        id: 'mainMenu'
    });
	
	var menuitem;
	var menuChilditem;
	var menuChilditemList=new Array();
		
	for(var i=0; i<menuRegion.length; i++)
	{
		
		menuChilditemList.push('<b class="menu-title">请选择分类</b>');
		for(var j=0; j<menuRegion[i].children.length; j++)
		{
			menuchilditem = createMenuChild(menuRegion[i].children[j]);
			menuChilditemList.push(menuchilditem);
			if(j==(menuRegion[i].children.length-1))
			{
				menuitem = createchildMenuList(menuChilditemList,menuRegion[i]);
				menu.add(menuitem);
				menuChilditemList.length=0;
			}
		}
	}
	
	//状态栏（包含菜单）
    var tb = new Ext.Toolbar();
    tb.render('toolbar');

    tb.add({
            text:'旅游类别管理',
            iconCls: 'bmenu',  // <-- icon
            menu: menu  // assign menu by instance
        }
        );
}



function createMenuChild(menuchilditem)
{
	var menuchilditemtemp = {
		id:menuchilditem.regionId,
        text: menuchilditem.name,
		stateId:menuchilditem.flag,
        handler: onItemClick
    }
	return menuchilditemtemp;
}

function createchildMenuList(menuChilditemList,menuRegion)
{
	menuChilditemListtemp = {
		text: menuRegion.name,
		menu: {        // <-- submenu by nested config object
        		items: menuChilditemList
              }
	}
	return menuChilditemListtemp;
}