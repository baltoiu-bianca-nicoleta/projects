from django.contrib import admin
from .models import Employee, Problem, CalfProblem, FNCProblem, WarehouseProblem, PremisesProblem

admin.site.register(Employee)
admin.site.register(Problem)
admin.site.register(CalfProblem)
admin.site.register(FNCProblem)
admin.site.register(WarehouseProblem)
admin.site.register(PremisesProblem)