<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Пользователи</h2>
    <div class="table-responsive">
        <table class="table table-hover table-dark table-bordered table-rounded">
            <thead class="thead-light">
            <tr>
                <th class="text-center" scope="col">ФИО</th>
                <th class="text-center" scope="col">Email</th>
                <th class="text-center" scope="col">Роли</th>
                <th class="text-center" scope="col">Подробная информация</th>
                <th class="text-center" scope="col">Подтверждение</th>
            </tr>
            </thead>
            <tbody>
            <#list users as user>
                <tr>
                    <td class="text-center">${user.lastName} ${user.firstName} ${user.middleName}</td>
                    <td class="text-center">${user.email}</td>
                    <td class="text-center"><#list user.roles as role><br>${role}</#list></td>
                    <td class="text-center">
                        <a class="link-info link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover" style="text-align: center; width: 100%"
                           data-toggle="collapse" href="#collapseExample${user.id}"
                           aria-expanded="true" aria-controls="collapseExample">
                            Посмотреть подробную информацию
                        </a>
                        <div class="collapse mb-3" id="collapseExample${user.id}" style="">
                            <#if user.isStudent()>
                                Группа: ${user.groups[0].name}
                            <#elseif user.isTeacher()>
                                Дисциплины:
                                <#list user.subjects as subject>
                                    <br>${subject.name}
                                </#list>
                            </#if>
                        </div>
                    </td>
                    <td class="text-center">
                        <#if user.checked>
                            <form action="/admin/ban/${user.id}" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <input type="submit" class="btn btn-danger btn-sm" value="Забанить">
                            </form>
                        <#else>
                            <form action="/admin/ban/${user.id}" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <input type="submit" class="btn btn-info btn-sm" value="Подтвердить">
                            </form>
                        </#if>
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <#if users?size == 0>
        <h3>Пользователей нет</h3>
    </#if>
</@t.template>
