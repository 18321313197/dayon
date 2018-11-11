<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${mapperTypeInfo.name}">

	<resultMap id="${entityTypeInfo.simpleName}" type="${entityTypeInfo.name}">
		<#list idColumnInfos as columnInfo>
		<id property="${columnInfo.attrName}" column="${columnInfo.column.name}" />
		</#list>
		<#list otherColumnInfos as columnInfo>
		<result property="${columnInfo.attrName}" column="${columnInfo.column.name}" />
		</#list>
	</resultMap>


	<select id="find" resultMap="${entityTypeInfo.simpleName}">
		select * ${tableName}
		<where>
			<#list otherColumnInfos as columnInfo>
				<#if columnInfo.column.type=='varchar' || columnInfo.column.type=='char' || columnInfo.column.type=='text'>
					<if ${columnInfo.attrName}!=null and ${columnInfo.attrName}!=''>
						and ${columnInfo.column.name}=${r'#{'}${columnInfo.attrName}${r'}'} 
					</if>
				<#elseif columnInfo.column.type!='date' && columnInfo.column.type!='datetime' && columnInfo.column.type!='time'>
					<if ${columnInfo.attrName}!=null>
						and ${columnInfo.column.name}=${r'#{'}${columnInfo.attrName}${r'}'} 
					</if>
				</#if>
			</#list>
		</where>
	</select>
	<select id="get" resultMap="${entityTypeInfo.simpleName}" >
		select * from ${tableName}
		where
			<#list idColumnInfos as columnInfo>
				<#if 0<columnInfo_index>and</#if> ${columnInfo.column.name}=${r'#{'}${columnInfo.attrName}${r'}'}
			</#list>
		
	</select>
	<insert id="add">
		insert into ${tableName}(
		<#list columnInfos as columnInfo>
			<#if 0<columnInfo_index>,</#if>${columnInfo.column.name}
		</#list>
		)
		value
		(
		<#list columnInfos as columnInfo>
			<#if 0<columnInfo_index>,</#if>${r'#{'}${columnInfo.attrName}${r'}'}
		</#list>
		)
	</insert>

	<update id="modify">
		update ${tableName}
		<set>
			<#list otherColumnInfos as columnInfo>
			<#if !columnInfo.column.isPrimary>
				<#if 0<columnInfo_index>,</#if> ${columnInfo.column.name}=${r'#{'}${columnInfo.attrName}${r'}'} 
			</#if>
			</#list>
		</set>
		where
			<#list idColumnInfos as columnInfo>
				<#if 0<columnInfo_index>and</#if> ${columnInfo.column.name}=${r'#{'}${columnInfo.attrName}${r'}'}
			</#list>

	</update>
	

	<update id="remove">
		delete from ${tableName} 
		where
			<#list idColumnInfos as columnInfo>
				<#if 0<columnInfo_index>and</#if> ${columnInfo.column.name}=${r'#{'}${columnInfo.attrName}${r'}'}
			</#list>
	</update>

</mapper>