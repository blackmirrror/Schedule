<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Направления, группы, дисциплины</h2>
    <hr>
    <a class="link-info link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample1"
       aria-expanded="true" aria-controls="collapseExample">
        Посмотреть группы
    </a>
    <div class="collapse mb-3" id="collapseExample1" style="">
        Группы:
        <#list groups as group>
            <br>${group.name} ${group.direction.code}
        </#list>
    </div>
    <br><br>Добавить новую группу
    <form action="/control/createGroup" method="post" class="form-inline">
        <div class="form-group">
            <input type="text" class="form-control" required id="name" name="name" placeholder="Название группы">
        </div>
        <div class="form-group mx-2">
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
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-dark">Добавить</button>
    </form>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
    <hr>

    <a class="link-info link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample2"
       aria-expanded="true" aria-controls="collapseExample">
        Посмотреть направления
    </a>
    <div class="collapse mb-3" id="collapseExample2" style="">
        Направления:
        <#list directions as direction>
            <br>${direction.code} ${direction.name}
        </#list>
    </div>

    <br><br>Добавить новое направление
    <form action="/control/createDirection" method="post" class="form-inline">
        <div class="form-group">
            <input type="text" class="form-control" required id="name" name="name" placeholder="Название направления">
        </div>
        <div class="form-group mx-2">
            <input type="text" class="form-control" required id="code" name="code" placeholder="Код направления">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-dark">Добавить</button>
    </form>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
    <hr>

    <a class="link-info link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample3"
       aria-expanded="true" aria-controls="collapseExample">
        Посмотреть дисциплины
    </a>
    <div class="collapse mb-3" id="collapseExample3" style="">
        Дисциплины:
        <#list subjects as subject>
            <br>${subject.name}
        </#list>
    </div>

    <br><br>Добавить новую дисциплину
    <form action="/control/createSubject" method="post" class="form-inline">
        <div class="form-group">
            <input type="text" class="form-control" required id="name" name="name" placeholder="Название дисциплины">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" class="btn btn-dark">Добавить</button>
    </form>
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
</@t.template>