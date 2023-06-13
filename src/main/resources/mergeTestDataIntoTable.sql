MERGE INTO %s AS TARGET
USING (VALUES %s) AS SOURCE
ON SOURCE.%s = TARGET.%s
WHEN NOT MATCHED BY SOURCE
AND TARGET.%s = %d THEN
DELETE
WHEN NOT MATCHED BY TARGET
AND SOURCE.%s != NULL THEN
INSERT (%s)
VALUES (%s)
;