-- USERS
insert into users(user_id, email, username, onboarding_status, last_provider, avatar_url)
values ('11111111-aaaa-4444-bbbb-1234567890ab', 'marco.rossi@example.com', 'marcorossi', 'COMPLETED',
        'GOOGLE', 'https://randomuser.me/api/portraits/men/79.jpg'),
       ('11111111-aaaa-4444-bbbb-1234567890ce', 'stefanobassani.dev@gmail.com', 'stefanodev', 'COMPLETED',
        'GOOGLE', 'https://lh3.googleusercontent.com/a/ACg8ocLJopDUhLlOB68Gf02xkyiF61D5j4dHs4WafKe7YP_i4XvhFZs=s96-c'),
       ('22222222-bbbb-5555-cccc-0987654321cd', 'luca.bianchi@example.com', 'lucabianchi', 'COMPLETED',
        'GOOGLE', 'https://randomuser.me/api/portraits/men/30.jpg');

-- ===========================
-- Macro Areas
-- ===========================
INSERT INTO job_macro_areas (job_macro_area_id, name)
VALUES ('550e8400-e29b-41d4-a716-446655440001', 'Tech'),
       ('550e8400-e29b-41d4-a716-446655440002', 'Design'),
       ('550e8400-e29b-41d4-a716-446655440003', 'Marketing'),
       ('550e8400-e29b-41d4-a716-446655440004', 'Product & Project'),
       ('550e8400-e29b-41d4-a716-446655440005', 'Business & Operations'),
       ('550e8400-e29b-41d4-a716-446655440006', 'Sales & Customer Success'),
       ('550e8400-e29b-41d4-a716-446655440007', 'People & HR'),
       ('550e8400-e29b-41d4-a716-446655440008', 'Finance & Legal'),
       ('550e8400-e29b-41d4-a716-446655440009', 'Executive & Leadership');

-- ===========================
-- Job Roles (110 totali)
-- ===========================
INSERT INTO job_roles (name, job_macro_area_id)
VALUES
-- TECH (1–21)
('Software Engineer', '550e8400-e29b-41d4-a716-446655440001'),
('Frontend Developer', '550e8400-e29b-41d4-a716-446655440001'),
('Backend Developer', '550e8400-e29b-41d4-a716-446655440001'),
('Full Stack Developer', '550e8400-e29b-41d4-a716-446655440001'),
('DevOps Engineer', '550e8400-e29b-41d4-a716-446655440001'),
('Data Scientist', '550e8400-e29b-41d4-a716-446655440001'),
('Machine Learning Engineer', '550e8400-e29b-41d4-a716-446655440001'),
('AI Engineer', '550e8400-e29b-41d4-a716-446655440001'),
('Data Analyst', '550e8400-e29b-41d4-a716-446655440001'),
('Data Engineer', '550e8400-e29b-41d4-a716-446655440001'),
('Cloud Engineer', '550e8400-e29b-41d4-a716-446655440001'),
('Security Engineer', '550e8400-e29b-41d4-a716-446655440001'),
('QA Engineer', '550e8400-e29b-41d4-a716-446655440001'),
('Mobile Developer', '550e8400-e29b-41d4-a716-446655440001'),
('iOS Developer', '550e8400-e29b-41d4-a716-446655440001'),
('Android Developer', '550e8400-e29b-41d4-a716-446655440001'),
('Web Developer', '550e8400-e29b-41d4-a716-446655440001'),
('Embedded Systems Engineer', '550e8400-e29b-41d4-a716-446655440001'),
('Game Developer', '550e8400-e29b-41d4-a716-446655440001'),
('Technical Architect', '550e8400-e29b-41d4-a716-446655440001'),
('CTO (Chief Technology Officer)', '550e8400-e29b-41d4-a716-446655440001'),

-- DESIGN (22–33)
('UX Designer', '550e8400-e29b-41d4-a716-446655440002'),
('UI Designer', '550e8400-e29b-41d4-a716-446655440002'),
('Product Designer', '550e8400-e29b-41d4-a716-446655440002'),
('Visual Designer', '550e8400-e29b-41d4-a716-446655440002'),
('Motion Designer', '550e8400-e29b-41d4-a716-446655440002'),
('Graphic Designer', '550e8400-e29b-41d4-a716-446655440002'),
('Brand Designer', '550e8400-e29b-41d4-a716-446655440002'),
('Interaction Designer', '550e8400-e29b-41d4-a716-446655440002'),
('Service Designer', '550e8400-e29b-41d4-a716-446655440002'),
('Design Researcher', '550e8400-e29b-41d4-a716-446655440002'),
('Design Lead', '550e8400-e29b-41d4-a716-446655440002'),
('Head of Design', '550e8400-e29b-41d4-a716-446655440002'),

-- MARKETING (34–47)
('Growth Marketer', '550e8400-e29b-41d4-a716-446655440003'),
('Digital Marketing Manager', '550e8400-e29b-41d4-a716-446655440003'),
('Performance Marketing Specialist', '550e8400-e29b-41d4-a716-446655440003'),
('Content Marketer', '550e8400-e29b-41d4-a716-446655440003'),
('SEO Specialist', '550e8400-e29b-41d4-a716-446655440003'),
('SEM Specialist', '550e8400-e29b-41d4-a716-446655440003'),
('Social Media Manager', '550e8400-e29b-41d4-a716-446655440003'),
('Community Manager', '550e8400-e29b-41d4-a716-446655440003'),
('Email Marketing Specialist', '550e8400-e29b-41d4-a716-446655440003'),
('Marketing Analyst', '550e8400-e29b-41d4-a716-446655440003'),
('Head of Marketing', '550e8400-e29b-41d4-a716-446655440003'),
('CMO (Chief Marketing Officer)', '550e8400-e29b-41d4-a716-446655440003'),
('Influencer Marketing Manager', '550e8400-e29b-41d4-a716-446655440003'),
('Copywriter', '550e8400-e29b-41d4-a716-446655440003'),

-- PRODUCT & PROJECT (48–56)
('Product Manager', '550e8400-e29b-41d4-a716-446655440004'),
('Technical Product Manager', '550e8400-e29b-41d4-a716-446655440004'),
('Product Owner', '550e8400-e29b-41d4-a716-446655440004'),
('Agile Coach', '550e8400-e29b-41d4-a716-446655440004'),
('Scrum Master', '550e8400-e29b-41d4-a716-446655440004'),
('Project Manager', '550e8400-e29b-41d4-a716-446655440004'),
('Program Manager', '550e8400-e29b-41d4-a716-446655440004'),
('Head of Product', '550e8400-e29b-41d4-a716-446655440004'),
('CPO (Chief Product Officer)', '550e8400-e29b-41d4-a716-446655440004'),

-- BUSINESS & OPERATIONS (57–68)
('Business Analyst', '550e8400-e29b-41d4-a716-446655440005'),
('Strategy Manager', '550e8400-e29b-41d4-a716-446655440005'),
('Operations Manager', '550e8400-e29b-41d4-a716-446655440005'),
('Business Development Manager', '550e8400-e29b-41d4-a716-446655440005'),
('Innovation Manager', '550e8400-e29b-41d4-a716-446655440005'),
('Chief of Staff', '550e8400-e29b-41d4-a716-446655440005'),
('General Manager', '550e8400-e29b-41d4-a716-446655440005'),
('COO (Chief Operating Officer)', '550e8400-e29b-41d4-a716-446655440005'),
('Marketplace Manager', '550e8400-e29b-41d4-a716-446655440005'),
('International Expansion Manager', '550e8400-e29b-41d4-a716-446655440005'),
('Category Manager', '550e8400-e29b-41d4-a716-446655440005'),
('Procurement Manager', '550e8400-e29b-41d4-a716-446655440005'),

-- SALES & CUSTOMER SUCCESS (69–80)
('Sales Development Representative (SDR)', '550e8400-e29b-41d4-a716-446655440006'),
('Account Executive', '550e8400-e29b-41d4-a716-446655440006'),
('Sales Manager', '550e8400-e29b-41d4-a716-446655440006'),
('Business Development Representative (BDR)', '550e8400-e29b-41d4-a716-446655440006'),
('Key Account Manager', '550e8400-e29b-41d4-a716-446655440006'),
('Head of Sales', '550e8400-e29b-41d4-a716-446655440006'),
('Customer Success Manager', '550e8400-e29b-41d4-a716-446655440006'),
('Customer Support Specialist', '550e8400-e29b-41d4-a716-446655440006'),
('Technical Account Manager', '550e8400-e29b-41d4-a716-446655440006'),
('Client Onboarding Specialist', '550e8400-e29b-41d4-a716-446655440006'),
('CRM Manager', '550e8400-e29b-41d4-a716-446655440006'),
('VP of Sales', '550e8400-e29b-41d4-a716-446655440006'),

-- PEOPLE & HR (81–90)
('HR Manager', '550e8400-e29b-41d4-a716-446655440007'),
('Talent Acquisition Specialist', '550e8400-e29b-41d4-a716-446655440007'),
('Technical Recruiter', '550e8400-e29b-41d4-a716-446655440007'),
('People Operations Manager', '550e8400-e29b-41d4-a716-446655440007'),
('Head of People', '550e8400-e29b-41d4-a716-446655440007'),
('HR Business Partner', '550e8400-e29b-41d4-a716-446655440007'),
('Employer Branding Specialist', '550e8400-e29b-41d4-a716-446655440007'),
('Culture Manager', '550e8400-e29b-41d4-a716-446655440007'),
('Learning & Development Manager', '550e8400-e29b-41d4-a716-446655440007'),
('Chief People Officer', '550e8400-e29b-41d4-a716-446655440007'),

-- FINANCE & LEGAL (91–100)
('Finance Manager', '550e8400-e29b-41d4-a716-446655440008'),
('Accountant', '550e8400-e29b-41d4-a716-446655440008'),
('Controller', '550e8400-e29b-41d4-a716-446655440008'),
('FP&A Analyst', '550e8400-e29b-41d4-a716-446655440008'),
('CFO (Chief Financial Officer)', '550e8400-e29b-41d4-a716-446655440008'),
('Legal Counsel', '550e8400-e29b-41d4-a716-446655440008'),
('Compliance Officer', '550e8400-e29b-41d4-a716-446655440008'),
('Tax Specialist', '550e8400-e29b-41d4-a716-446655440008'),
('Corporate Lawyer', '550e8400-e29b-41d4-a716-446655440008'),
('Paralegal', '550e8400-e29b-41d4-a716-446655440008'),

-- EXECUTIVE & LEADERSHIP (101–110)
('CEO (Chief Executive Officer)', '550e8400-e29b-41d4-a716-446655440009'),
('Founder', '550e8400-e29b-41d4-a716-446655440009'),
('Co-Founder', '550e8400-e29b-41d4-a716-446655440009'),
('Managing Director', '550e8400-e29b-41d4-a716-446655440009'),
('VP of Operations', '550e8400-e29b-41d4-a716-446655440009'),
('VP of Engineering', '550e8400-e29b-41d4-a716-446655440009'),
('VP of Marketing', '550e8400-e29b-41d4-a716-446655440009'),
('VP of Product', '550e8400-e29b-41d4-a716-446655440009'),
('Board Member', '550e8400-e29b-41d4-a716-446655440009'),
('Startup Advisor', '550e8400-e29b-41d4-a716-446655440009');

-- ===========================
-- PROJECTS (fittizi)
-- ===========================
INSERT INTO projects (project_id, title, overview, owner_id)
VALUES
    ('aaaaaaa1-0000-0000-0000-aaaaaaaa0001', 'Project Alpha', 'Sviluppo di una piattaforma SaaS per la gestione dei dati.', '11111111-aaaa-4444-bbbb-1234567890ab'),
    ('aaaaaaa2-0000-0000-0000-aaaaaaaa0002', 'Project Beta', 'Applicazione mobile per il tracciamento della salute e fitness.', '11111111-aaaa-4444-bbbb-1234567890ce'),
    ('aaaaaaa3-0000-0000-0000-aaaaaaaa0003', 'Project Gamma', 'Tool di analisi marketing basato su AI.', '22222222-bbbb-5555-cccc-0987654321cd'),
    ('aaaaaaa4-0000-0000-0000-aaaaaaaa0004', 'Project Delta', 'Sistema di e-commerce con funzionalità avanzate di pagamento.', '11111111-aaaa-4444-bbbb-1234567890ab'),
    ('aaaaaaa5-0000-0000-0000-aaaaaaaa0005', 'Project Epsilon', 'Piattaforma collaborativa per team remoti.', '11111111-aaaa-4444-bbbb-1234567890ce'),
    ('aaaaaaa6-0000-0000-0000-aaaaaaaa0006', 'Project Zeta', 'Soluzione IoT per smart home.', '22222222-bbbb-5555-cccc-0987654321cd'),
    ('aaaaaaa7-0000-0000-0000-aaaaaaaa0007', 'Project Eta', 'Marketplace verticale per professionisti creativi.', '11111111-aaaa-4444-bbbb-1234567890ab'),
    ('aaaaaaa8-0000-0000-0000-aaaaaaaa0008', 'Project Theta', 'CRM avanzato con AI per gestione clienti.', '11111111-aaaa-4444-bbbb-1234567890ce'),
    ('aaaaaaa9-0000-0000-0000-aaaaaaaa0009', 'Project Iota', 'Piattaforma di e-learning personalizzata.', '22222222-bbbb-5555-cccc-0987654321cd'),
    ('aaaaaa10-0000-0000-0000-aaaaaaaa0010', 'Project Kappa', 'App per eventi e networking professionale.', '11111111-aaaa-4444-bbbb-1234567890ab'),
    ('aaaaaa11-0000-0000-0000-aaaaaaaa0011', 'Project Lambda', 'Sistema di monitoraggio ambientale IoT.', '22222222-bbbb-5555-cccc-0987654321cd'),
    ('aaaaaa12-0000-0000-0000-aaaaaaaa0012', 'Project Mu', 'Dashboard analitica per dati aziendali.', '11111111-aaaa-4444-bbbb-1234567890ce');

-- ===========================
-- USER JOB ROLES (fittizi)
-- ===========================
INSERT INTO user_job_roles (job_role_id, user_id)
VALUES
-- Marco Rossi
(1, '11111111-aaaa-4444-bbbb-1234567890ab'),   -- Software Engineer
(10, '11111111-aaaa-4444-bbbb-1234567890ab'),  -- Data Engineer
(22, '11111111-aaaa-4444-bbbb-1234567890ab'),  -- UX Designer

-- Stefano Dev
(4, '11111111-aaaa-4444-bbbb-1234567890ce'),   -- Full Stack Developer
(32, '11111111-aaaa-4444-bbbb-1234567890ce'),  -- Design Lead
(48, '11111111-aaaa-4444-bbbb-1234567890ce'),  -- Product Manager

-- Luca Bianchi
(6, '22222222-bbbb-5555-cccc-0987654321cd'),   -- Data Scientist
(34, '22222222-bbbb-5555-cccc-0987654321cd'),  -- Growth Marketer
(70, '22222222-bbbb-5555-cccc-0987654321cd');  -- Account Executive


-- ===========================
-- PROJECT JOB ROLES (fittizi)
-- ===========================
INSERT INTO project_job_roles (job_role_id, project_id)
VALUES
-- Project Alpha
(1, 'aaaaaaa1-0000-0000-0000-aaaaaaaa0001'),  -- Software Engineer
(10, 'aaaaaaa1-0000-0000-0000-aaaaaaaa0001'), -- Data Engineer
(32, 'aaaaaaa1-0000-0000-0000-aaaaaaaa0001'), -- Design Lead

-- Project Beta
(4, 'aaaaaaa2-0000-0000-0000-aaaaaaaa0002'),  -- Full Stack Developer
(48, 'aaaaaaa2-0000-0000-0000-aaaaaaaa0002'), -- Product Manager
(22, 'aaaaaaa2-0000-0000-0000-aaaaaaaa0002'), -- UX Designer

-- Project Gamma
(6, 'aaaaaaa3-0000-0000-0000-aaaaaaaa0003'),  -- Data Scientist
(34, 'aaaaaaa3-0000-0000-0000-aaaaaaaa0003'), -- Growth Marketer
(70, 'aaaaaaa3-0000-0000-0000-aaaaaaaa0003'), -- Account Executive

-- Project Delta
(4, 'aaaaaaa4-0000-0000-0000-aaaaaaaa0004'),  -- Full Stack Developer
(32, 'aaaaaaa4-0000-0000-0000-aaaaaaaa0004'), -- Design Lead

-- Project Epsilon
(32, 'aaaaaaa5-0000-0000-0000-aaaaaaaa0005'), -- Design Lead
(48, 'aaaaaaa5-0000-0000-0000-aaaaaaaa0005'), -- Product Manager

-- Project Zeta
(1, 'aaaaaaa6-0000-0000-0000-aaaaaaaa0006'),  -- Software Engineer
(10, 'aaaaaaa6-0000-0000-0000-aaaaaaaa0006'); -- Data Engineer


-- ===========================
-- Project Join Requests (fittizie)
-- ===========================
INSERT INTO project_join_requests (join_request_id, requester_id, project_id, request_status)
VALUES
    ('aaaaaaaa-0000-0000-0000-000000000101', '11111111-aaaa-4444-bbbb-1234567890ab', 'aaaaaaa1-0000-0000-0000-aaaaaaaa0001', 'PENDING'),
    ('aaaaaaaa-0000-0000-0000-000000000102', '11111111-aaaa-4444-bbbb-1234567890ce', 'aaaaaaa2-0000-0000-0000-aaaaaaaa0002', 'ACCEPTED'),
    ('aaaaaaaa-0000-0000-0000-000000000103', '22222222-bbbb-5555-cccc-0987654321cd', 'aaaaaaa3-0000-0000-0000-aaaaaaaa0003', 'REJECTED'),
    ('aaaaaaaa-0000-0000-0000-000000000104', '11111111-aaaa-4444-bbbb-1234567890ce', 'aaaaaaa4-0000-0000-0000-aaaaaaaa0004', 'PENDING'),
    ('aaaaaaaa-0000-0000-0000-000000000105', '22222222-bbbb-5555-cccc-0987654321cd', 'aaaaaaa5-0000-0000-0000-aaaaaaaa0005', 'PENDING'),
    ('aaaaaaaa-0000-0000-0000-000000000106', '22222222-bbbb-5555-cccc-0987654321cd', 'aaaaaaa4-0000-0000-0000-aaaaaaaa0004', 'PENDING');

-- ===========================
-- Project Users (fittizie)
-- ===========================

INSERT INTO project_users (user_id, project_id)
values ('22222222-bbbb-5555-cccc-0987654321cd', 'aaaaaaa3-0000-0000-0000-aaaaaaaa0003'),
       ('11111111-aaaa-4444-bbbb-1234567890ce', 'aaaaaaa3-0000-0000-0000-aaaaaaaa0003')
