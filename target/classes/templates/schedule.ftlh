<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Расписание</h2>

    <#if user.isAdmin()>
        <hr>
        Добавить новую единицу
        <form action="/schedule/create" method="post" class="form-inline my-2">
            <input type="number" value="${user.id}" name="userId" id="userId" hidden="hidden">
            <div class="form-group">
                <input type="number" class="form-control" required id="semester" name="semester" placeholder="Семестр">
            </div>
            <div class="form-group mx-2">
                <label for="groupId">Группа</label>
                <select class="form-control" id="groupId" name="groupId">
                    <#list groups as group>
                        <option value="${group.id}">${group.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <input type="number" class="form-control" required id="pair" name="pair" placeholder="Пара">
            </div>
            <div class="form-group">
                <input type="number" class="form-control mx-2" required id="dayOfWeek" name="dayOfWeek"
                       placeholder="День недели">
            </div>
            <div class="form-group mx-2 my-2">
                <label for="subjectId">Дисциплина</label>
                <select class="form-control" id="subjectId" name="subjectId">
                    <#list subjects as subject>
                        <option value="${subject.id}">${subject.name}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group mx-2 my-2">
                <label for="teacherId">Преподаватель</label>
                <select class="form-control" id="teacherId" name="teacherId">
                    <#list teachers as teacher>
                        <option value="${teacher.id}">${teacher.getInitials()}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group mx-2 my-2">
                <label for="classroomId">Аудитория</label>
                <select class="form-control" id="classroomId" name="classroomId">
                    <#list classrooms as classroom>
                        <option value="${classroom.id}">${classroom.number}</option>
                    </#list>
                </select>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-dark">Добавить</button>
        </form>
        <#if errorMessage??>
            <h6 style="color: red">${errorMessage}</h6>
        </#if>
    </#if>

    <#if user.isStudent() || user.isTeacher()>
        <hr>
        <form action="/scheduleSome" method="get" class="form-inline my-2">
            <div class="form-group mx-2">
                <label for="groupId">Группа</label>
                <select class="form-control" id="groupId" name="groupId">
                    <#list groups as group>
                        <option value="${group.id}">${group.name}</option>
                    </#list>
                </select>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-dark">Показать</button>
        </form>

        <form action="/scheduleSome" method="get" class="form-inline my-2">
            <div class="form-group mx-2">
                <label for="teacherId">Преподаватель</label>
                <select class="form-control" id="teacherId" name="teacherId">
                    <#list teachers as teacher>
                        <option value="${teacher.id}">${teacher.getInitials()}</option>
                    </#list>
                </select>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit" class="btn btn-dark">Показать</button>
        </form>
        <hr>
    </#if>


    <#if user.isStudent()>
        <h4 class="text-center">Группа ${user.getGroup().name}</h4>
        <div class="container">
            <#assign numbers = [1, 2, 3, 4, 5, 6]>
            <#list numbers as number>
                <#if number % 2 == 1>
                    <div class="row">
                </#if>

                <div class="col-md-6">
                    <h4 class="text-center">${textFormatter.getDayByNumber(number)}</h4>
                    <table class="table table-hover table-dark table-bordered table-rounded">
                        <thead class="thead-light">
                        <tr>
                            <th class="text-center" scope="col">№</th>
                            <th class="text-center" scope="col">Время</th>
                            <th class="text-center" scope="col">Предмет</th>
                            <th class="text-center" scope="col">Преподаватель</th>
                            <th class="text-center" scope="col">Ауд.</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list 1..7 as pairNumber>
                            <#assign foundSchedule = schedules?filter(schedule -> schedule.pair == pairNumber && schedule.dayOfWeek == number) />
                            <#if foundSchedule?has_content>
                                <#list foundSchedule as schedule>
                                    <tr>
                                        <td>${schedule.pair}</td>
                                        <td>${schedule.getTimeByPair()}</td>
                                        <td>${schedule.subject.getShortName()}</td>
                                        <td>${schedule.teacher.getInitials()}</td>
                                        <td>${schedule.classroom.number}</td>
                                    </tr>
                                </#list>
                            <#else>
                                <tr>
                                    <td>${pairNumber}</td>
                                    <td>${textFormatter.getTimeByPair(pairNumber)}</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </#if>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#if number % 2 == 0>
                    </div>
                </#if>
            </#list>
        </div>
    </#if>

    <#if user.isTeacher()>
        <h4 class="text-center">Преподаватель ${user.getInitials()}</h4>
        <div class="container">
            <#assign numbers = [1, 2, 3, 4, 5, 6]>
            <#list numbers as number>
                <#if number % 2 == 1>
                    <div class="row">
                </#if>

                <div class="col-md-6">
                    <h4 class="text-center">${textFormatter.getDayByNumber(number)}</h4>
                    <table class="table table-hover table-dark table-bordered table-rounded">
                        <thead class="thead-light">
                        <tr>
                            <th class="text-center" scope="col">№</th>
                            <th class="text-center" scope="col">Время</th>
                            <th class="text-center" scope="col">Предмет</th>
                            <th class="text-center" scope="col">Группа</th>
                            <th class="text-center" scope="col">Ауд.</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list 1..7 as pairNumber>
                            <#assign foundSchedule = schedules?filter(schedule -> schedule.pair == pairNumber && schedule.dayOfWeek == number) />
                            <#if foundSchedule?has_content>
                                <#list foundSchedule as schedule>
                                    <tr>
                                        <td>${schedule.pair}</td>
                                        <td>${schedule.getTimeByPair()}</td>
                                        <td>${schedule.subject.getShortName()}</td>
                                        <td>${schedule.group.name}</td>
                                        <td>${schedule.classroom.number}</td>
                                    </tr>
                                </#list>
                            <#else>
                                <tr>
                                    <td>${pairNumber}</td>
                                    <td>${textFormatter.getTimeByPair(pairNumber)}</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </#if>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <#if number % 2 == 0>
                    </div>
                </#if>
            </#list>
        </div>
    </#if>

</@t.template>