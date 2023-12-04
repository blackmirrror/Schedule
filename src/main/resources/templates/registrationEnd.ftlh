<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Пожалуйста, заполните оставшиеся данные для успешной регистрации</h1>
    <hr>
    <form action="/registrationEndStudent" method="post" class="form-inline">
        <div class="form-group mx-2">
            <input type="hidden" name="userId" value="${user.id}">
            <label for="groupId">Группа</label>
            <select class="form-control" id="groupId" name="groupId">
                <#list groups as group>
                    <option value="${group.id}">${group.name}</option>
                </#list>
            </select>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-dark">Закончить регистрацию</button>
    </form>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
</@t.template>