<#macro search>
	<div class="header-search">
			<div class="inner">
			<fieldset>
			<legend>兔游一下</legend>
				<form id="search-form-bottom" action="${base}/search.shtml" target="_blank">

					<div class="s1">
							<span>
							<select name="searchType">
								<#if (searchType??)&&(searchType==1)>
								<OPTION value=0 >线路搜索</OPTION> 
								<OPTION value=1 selected>景点搜索</OPTION> 	
								<#else>
								<OPTION value=0 selected>线路搜索</OPTION> 
								<OPTION value=1>景点搜索</OPTION> 
								</#if>
							</select>
							</span>
						</div>

					<div class="s2">

						<input name="keyWord" value="" accesskey="s" type="text">
					</div>

				


					<div class="s4">
					<button type="submit">兔游一下</button>
					</div>

				</form>
			</fieldset>
			</div>
		</div>
</#macro>