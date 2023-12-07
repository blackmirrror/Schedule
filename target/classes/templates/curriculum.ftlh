<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Учебный план</h2>

    <#if user.isAdmin()>
        <hr>
        Добавить новую единицу
        <form action="/curriculum/create" method="post" class="form-inline my-2">
            <div class="form-group">
                <input type="number" class="form-control" required id="semester" name="semester" placeholder="Семестр">
            </div>
            <div class="form-group mx-2">
                <label for="directionId">Направление</label>
                <select class="form-control" id="directionId" name="directionId">
                    <#list directions as direction>
                        <option value="${direction.id}">${direction.code}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group mx-2 my-2">
                <label for="directionId">Дисциплина</label>
                <select class="form-control" id="subjectId" name="subjectId">
                    <#list subjects as subject>
                        <option value="${subject.id}">${subject.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group mx-2">
                <input type="number" class="form-control" required id="countPerSemester" name="countPerSemester" placeholder="Кол-во в семестр">
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-dark">Добавить</button>
        </form>
        <#if errorMessage??>
            <h2 style="color: red">${errorMessage}</h2>
        </#if>
    </#if>

    <div class="table-responsive">
        <table class="table table-hover table-dark table-bordered table-rounded">
            <thead class="thead-light">
            <tr>
                <th class="text-center" scope="col">Семестр</th>
                <th class="text-center" scope="col">Направление</th>
                <th class="text-center" scope="col">Дисциплина</th>
                <th class="text-center" scope="col">Ед./сем.</th>
            </thead>
            <tbody>
            <#list curriculums as curriculum>
                <tr>
                    <td class="text-center">${curriculum.semester}</td>
                    <td class="text-center">${curriculum.direction.code}</td>
                    <td class="text-center">${curriculum.subject.name}</td>
                    <td class="text-center">${curriculum.countPerSemester}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>

</@t.template>