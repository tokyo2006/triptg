package com.yeoou.components.acegi;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

/**
 * @deprecated
 * @author kensin
 *
 */
public class MyUserDetail implements UserDetails
{

	private static final long serialVersionUID = 1L;

	private String password;
	private String username;
	private boolean enabled;
	private GrantedAuthority[] authorities;
	
	public boolean isEnabled()
	{
		return enabled;
	}



	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}



	public String getPassword()
	{
		return password;
	}



	public void setPassword(String password)
	{
		this.password = password;
	}



	public String getUsername()
	{
		return username;
	}



	public void setUsername(String username)
	{
		this.username = username;
	}







	public boolean isAccountNonExpired()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAccountNonLocked()
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isCredentialsNonExpired()
	{
		// TODO Auto-generated method stub
		return false;
	}



	public GrantedAuthority[] getAuthorities()
	{
		return authorities;
	}



	public void setAuthorities(GrantedAuthority[] authorities)
	{
		this.authorities = authorities;
	}
	public boolean equals(Object obj) {	

		if (!(obj instanceof MyUserDetail)) return false;

		MyUserDetail user = (MyUserDetail) obj;
		if (null == user.getUsername()) return false;

		return user.getUsername().equals(username);
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		int hashCode = 17;
		if (null != username) hashCode = 37 * hashCode + username.hashCode();
		return hashCode;
	}

}
