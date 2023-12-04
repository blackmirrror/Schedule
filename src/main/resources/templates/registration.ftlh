<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Регистрация</h1>
    <hr>
    <form action="/registration" method="post">
        <div class="form-group">
            <label for="lastName">Фамилия</label>
            <input type="text" class="form-control" required id="lastName" name="lastName">
            <label for="firstName">Имя</label>
            <input type="text" class="form-control" required id="firstName" name="firstName">
            <label for="middleName">Отчество</label>
            <input type="text" class="form-control" required id="middleName" name="middleName">
        </div>
        <div class="form-group">
            <label for="email">Электронная почта</label>
            <input type="email" class="form-control" id="email" required name="email">
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control" id="password" required name="password">
        </div>
        <div class="form-group">
            <label for="role">Роль</label>
            <select class="form-control" id="role" name="role">
                <option value="STUDENT">Студент</option>
                <option value="TEACHER">Преподаватель</option>
            </select>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Зарегистрироваться</button>
    </form>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
</@t.template>