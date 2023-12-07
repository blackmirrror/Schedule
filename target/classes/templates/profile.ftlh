<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Личный кабинет</h2>
    <hr>
    <br>
    <div style="text-align: center">
        <img src="/static/images/avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.lastName} ${user.firstName} ${user.middleName}</h2>
        <br>
        Электоронная почта: <b>${user.email}</b>

        <#if user.isStudent()>
            <#if user.groups?? && user.groups?size != 0>
                <#list user.groups as group>
                    <br>Группа: <b>${group.name}</b>
                </#list>
            <#else>
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
            </#if>
        </#if>

        <#if user.isTeacher()>
            <#if user.subjects?? && user.subjects?size != 0>
                <br>Дисциплины:
                <#list user.subjects as subject>
                    <br><b>${subject.name}</b>
                </#list>
            <#else>
                <hr>
                <form action="/registrationEndTeacher" method="post">
                    <input type="hidden" name="userId" value="${user.id}">
                    <#list subjects as subject>
                        <div>
                            <input type="checkbox" id="subject_${subject.id}" name="selectedSubjects" value="${subject.id}">
                            <label for="subject_${subject.id}">${subject.name}</label>
                        </div>
                    </#list>
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit" class="btn btn-dark">Закончить регистрацию</button>
                </form>
                <#if errorMessage??>
                    <h2 style="color: red">${errorMessage}</h2>
                </#if>
            </#if>
        </#if>

        <br>
        <br>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Выйти из аккаунта
            </button>
        </form>
    </div>
</@t.template>