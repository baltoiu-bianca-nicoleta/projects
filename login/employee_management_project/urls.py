from django.urls import path
from . import views

urlpatterns = [
    path('add_problem/', views.add_problem, name='add_problem'),
    path('generate_report/', views.generate_report, name='generate_report'),
]

