function doJSON(stringData) {
		try {
			var jsonData = Ext.util.JSON.decode(stringData);
			return jsonData;
		}
		catch (err) {
			Ext.MessageBox.alert('ERROR', 'Could not decode ' + stringData);
			window.close();
		}
	}

